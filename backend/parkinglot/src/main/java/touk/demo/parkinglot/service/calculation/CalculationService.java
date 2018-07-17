package touk.demo.parkinglot.service.calculation;

import touk.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import touk.demo.parkinglot.model.response.ServiceResponse;

public interface CalculationService {

  ServiceResponse getCurrentFee(int id) throws InvalidSpotIdNumberException;
}
