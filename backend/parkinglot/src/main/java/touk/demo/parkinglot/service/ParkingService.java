package touk.demo.parkinglot.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.converter.BaseConverter;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.entity.Spots;
import touk.demo.parkinglot.repository.DriverTypeRepository;
import touk.demo.parkinglot.repository.ParkingSpotRepository;
import touk.demo.parkinglot.repository.SpotsRepository;

@Service
public class ParkingService implements ManagementService {

  private final SpotsRepository spotsRepository;
  private final DriverTypeRepository driverTypeRepository;
  private final ParkingSpotRepository parkingSpotRepository;
  private final BaseConverter<List<Spots>, ParkingSpotInfo> parkingInfoConverter;
  private final BaseConverter<Spots, FreeSpotInfo> freeSpotConverter;
  private final BaseConverter<Spots, OccupiedSpotInfo> occupiedConverter;
  private final BaseConverter<ParkingSpot, ReservationConfirm> reservationConfirmConverter;

  @Autowired
  public ParkingService(
      SpotsRepository spotsRepository,
      DriverTypeRepository driverTypeRepository,
      ParkingSpotRepository parkingSpotRepository,
      BaseConverter<List<Spots>, ParkingSpotInfo> parkingInfoConverter,
      BaseConverter<Spots, FreeSpotInfo> freeSpotConverter,
      BaseConverter<Spots, OccupiedSpotInfo> occupiedConverter,
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
  public ReservationConfirm postSpotReservation(String driver, String carNumber) {
    ParkingSpot spot = new ParkingSpot();

    spot.setDriverType(driverTypeRepository.findById(driver).get());
    spot.setCarNumber(carNumber);
    spot.setOccupied(true);
    spot.setStartDate(LocalDate.now());
    spot.setId(1);

    return reservationConfirmConverter.convert(parkingSpotRepository.save(spot));
  }
}
