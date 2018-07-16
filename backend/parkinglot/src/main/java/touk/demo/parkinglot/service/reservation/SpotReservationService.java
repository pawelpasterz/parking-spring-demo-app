package touk.demo.parkinglot.service.reservation;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import touk.demo.parkinglot.converter.BaseConverter;
import touk.demo.parkinglot.model.dto.ClosingFeeValue;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.entity.Spot;
import touk.demo.parkinglot.model.error.NoSpotAvailable;
import touk.demo.parkinglot.model.response.ServiceResponse;
import touk.demo.parkinglot.repository.DriverTypeRepository;
import touk.demo.parkinglot.repository.ParkingSpotRepository;
import touk.demo.parkinglot.repository.SpotsRepository;

@Service
public class SpotReservationService implements ReservationService {

  private final DriverTypeRepository driverTypeRepository;
  private final SpotsRepository spotsRepository;
  private final ParkingSpotRepository parkingSpotRepository;
  private final BaseConverter<ParkingSpot, ReservationConfirm> reservationConfirmConverter;

  @Autowired
  public SpotReservationService(
      DriverTypeRepository driverTypeRepository,
      SpotsRepository spotsRepository,
      ParkingSpotRepository parkingSpotRepository,
      BaseConverter<ParkingSpot, ReservationConfirm> reservationConfirmConverter) {
    this.driverTypeRepository = driverTypeRepository;
    this.spotsRepository = spotsRepository;
    this.parkingSpotRepository = parkingSpotRepository;
    this.reservationConfirmConverter = reservationConfirmConverter;
  }

  @Transactional
  @Override
  public ServiceResponse postSpotReservation(String driver, String carNumber) {
    if (hasFreeSpot()) {
      return NoSpotAvailable.getInstance();
    }

    ParkingSpot spot = new ParkingSpot();

    spot.setDriverType(driverTypeRepository.findById(driver).get());
    spot.setCarNumber(carNumber);
    spot.setOccupied(true);
    spot.setStartDate(new Date());
    spot.setId(findFirstAvailableSpot());

    increaseByOne("occupied");
    decreaseByOne("free");

    return reservationConfirmConverter.convert(parkingSpotRepository.save(spot));
  }

  @Transactional
  @Override
  public ServiceResponse closeSpotReservation(int id, CurrentFeeValue response) {
    ClosingFeeValue closingFeeValue = new ClosingFeeValue();

    closingFeeValue.setFee(response.getFee());
    closingFeeValue.setId(id);

    deleteSpotReservation(id);

    return closingFeeValue;
  }

  private void deleteSpotReservation(int id) {

    ParkingSpot spot = new ParkingSpot();
    spot.setId(id);
    spot.setOccupied(false);
    spot.setStartDate(null);
    spot.setDriverType(null);
    spot.setCarNumber(null);

    parkingSpotRepository.save(spot);

    decreaseByOne("occupied");
    increaseByOne("free");
  }

  private int findFirstAvailableSpot() {
    return parkingSpotRepository.findAllByOccupiedOrderByIdAsc(false).findFirst().get().getId();
  }

  private boolean hasFreeSpot() {
    return spotsRepository.findById("free").get().getCountNumber() == 0;
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
