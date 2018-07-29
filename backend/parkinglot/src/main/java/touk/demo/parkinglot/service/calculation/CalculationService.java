package touk.demo.parkinglot.service.calculation;

import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import touk.demo.parkinglot.model.response.ServiceResponse;

public interface CalculationService {

  CurrentFeeValue getCurrentFee(int id) throws InvalidSpotIdNumberException;
}
