package pl.pasterz.demo.parkinglot.service.calculation;

import pl.pasterz.demo.parkinglot.model.dto.CurrentFeeValue;
import pl.pasterz.demo.parkinglot.model.error.InvalidSpotIdNumberException;

public interface CalculationService {

  CurrentFeeValue getCurrentFee(int id) throws InvalidSpotIdNumberException;
}
