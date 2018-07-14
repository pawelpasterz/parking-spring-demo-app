package touk.demo.parkinglot.service.reservation;

import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.response.ServiceResponse;

public interface ReservationService {

  ServiceResponse postSpotReservation(String driver, String carNumber);
  ServiceResponse closeSpotReservation(int id, ServiceResponse currentFeeValue);
}
