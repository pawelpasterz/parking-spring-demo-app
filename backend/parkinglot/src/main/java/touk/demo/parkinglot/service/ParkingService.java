package touk.demo.parkinglot.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import touk.demo.parkinglot.converter.BaseConverter;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.entity.Spot;
import touk.demo.parkinglot.model.errors.NoSpotAvailable;
import touk.demo.parkinglot.model.interfaces.ParkingServiceResponse;
import touk.demo.parkinglot.repository.DriverTypeRepository;
import touk.demo.parkinglot.repository.ParkingSpotRepository;
import touk.demo.parkinglot.repository.SpotsRepository;

@Service
public class ParkingService implements ManagementService {

  private final SpotsRepository spotsRepository;
  private final DriverTypeRepository driverTypeRepository;
  private final ParkingSpotRepository parkingSpotRepository;
  private final BaseConverter<List<Spot>, ParkingSpotInfo> parkingInfoConverter;
  private final BaseConverter<Spot, FreeSpotInfo> freeSpotConverter;
  private final BaseConverter<Spot, OccupiedSpotInfo> occupiedConverter;
  private final BaseConverter<ParkingSpot, ReservationConfirm> reservationConfirmConverter;

  @Autowired
  public ParkingService(
      SpotsRepository spotsRepository,
      DriverTypeRepository driverTypeRepository,
      ParkingSpotRepository parkingSpotRepository,
      BaseConverter<List<Spot>, ParkingSpotInfo> parkingInfoConverter,
      BaseConverter<Spot, FreeSpotInfo> freeSpotConverter,
      BaseConverter<Spot, OccupiedSpotInfo> occupiedConverter,
      BaseConverter<ParkingSpot, ReservationConfirm> reservationConfirmConverter) {
    this.spotsRepository = spotsRepository;
    this.driverTypeRepository = driverTypeRepository;
    this.parkingSpotRepository = parkingSpotRepository;
    this.parkingInfoConverter = parkingInfoConverter;
    this.freeSpotConverter = freeSpotConverter;
    this.occupiedConverter = occupiedConverter;
    this.reservationConfirmConverter = reservationConfirmConverter;
  }

  @Override
  public ParkingSpotInfo getParkingInfo() {
    return parkingInfoConverter.convert(spotsRepository.findAll());
  }

  @Override
  public FreeSpotInfo getFreeSpotNumber() {
    return freeSpotConverter.convert(spotsRepository.findById("free").get());
  }

  @Override
  public OccupiedSpotInfo getOccupiedSpotNumber() {
    return occupiedConverter.convert(spotsRepository.findById("occupied").get());
  }

  @Override
  @Transactional
  public ParkingServiceResponse postSpotReservation(String driver, String carNumber) {
    if (hasFreeSpot()) {
      return NoSpotAvailable.getInstance();
    }

    ParkingSpot spot = new ParkingSpot();

    spot.setDriverType(driverTypeRepository.findById(driver).get());
    spot.setCarNumber(carNumber);
    spot.setOccupied(true);
    spot.setStartDate(LocalDate.now());
    spot.setId(findFirstAvailableSpot());

    increaseByOne("occupied");
    decreaseByOne("free");

    return reservationConfirmConverter.convert(parkingSpotRepository.save(spot));
  }

  private int findFirstAvailableSpot() {
    return parkingSpotRepository
        .findAllByOccupiedOrderByIdAsc(false)
        .findFirst()
        .get()
        .getId();
  }

  private boolean hasFreeSpot() {
    return spotsRepository
        .findById("free")
        .get()
        .getCountNumber() == 0;
  }

  private void increaseByOne(String spotType) {
    Spot spot = spotsRepository.findById(spotType).get();
    spot.setCountNumber(spot.getCountNumber() + 1);
    spotsRepository.save(spot);
  }

  private void decreaseByOne(String spotType) {
    Spot spot = spotsRepository.findById(spotType).get();
    spot.setCountNumber(spot.getCountNumber() - 1);
    spotsRepository.save(spot);
  }
}
