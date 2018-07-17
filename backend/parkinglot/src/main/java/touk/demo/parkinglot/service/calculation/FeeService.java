package touk.demo.parkinglot.service.calculation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.calculator.Calculator;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import touk.demo.parkinglot.model.response.ServiceResponse;
import touk.demo.parkinglot.repository.ParkingSpotRepository;

@Service
public class FeeService implements CalculationService {

  private final ParkingSpotRepository repository;
  private final Calculator<ParkingSpot, CurrentFeeValue> calculator;

  @Autowired
  public FeeService(ParkingSpotRepository repository,
      Calculator<ParkingSpot, CurrentFeeValue> calculator) {
    this.repository = repository;
    this.calculator = calculator;
  }

  @Override
  public ServiceResponse getCurrentFee(int id) throws InvalidSpotIdNumberException {
    return repository.findById(id)
        .filter(ParkingSpot::isOccupied)
        .map(calculator::getFeeValue)
        .orElseThrow(InvalidSpotIdNumberException::new);
  }
}
