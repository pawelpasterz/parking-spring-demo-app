package touk.demo.parkinglot.service.reservation;

import touk.demo.parkinglot.model.dto.ClosingFeeValue;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.dto.ReservationConfirm;

public interface ReservationService {

  ReservationConfirm postSpotReservation(String driver, String carNumber);
  ClosingFeeValue closeSpotReservation(int id, CurrentFeeValue currentFeeValue);
}
