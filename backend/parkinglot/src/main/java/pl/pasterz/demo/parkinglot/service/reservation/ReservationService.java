package pl.pasterz.demo.parkinglot.service.reservation;

import pl.pasterz.demo.parkinglot.model.dto.CurrentFeeValue;
import pl.pasterz.demo.parkinglot.model.dto.ReservationConfirm;
import pl.pasterz.demo.parkinglot.model.dto.ClosingFeeValue;

public interface ReservationService {

  ReservationConfirm postSpotReservation(String driver, String carNumber);
  ClosingFeeValue closeSpotReservation(int id, CurrentFeeValue currentFeeValue);
}
