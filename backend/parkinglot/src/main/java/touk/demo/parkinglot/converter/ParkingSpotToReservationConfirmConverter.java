package touk.demo.parkinglot.converter;

import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.entity.ParkingSpot;

@Component
public class ParkingSpotToReservationConfirmConverter implements BaseConverter<ParkingSpot, ReservationConfirm> {

  @Override
  public ReservationConfirm convert(ParkingSpot from) {
    ReservationConfirm reservation = new ReservationConfirm();
    reservation.setBeginTime(from.getStartDate());
    reservation.setDriverType(from.getDriverType().getRoleName());
    reservation.setSpotId(from.getId());

    return reservation;
  }
}
