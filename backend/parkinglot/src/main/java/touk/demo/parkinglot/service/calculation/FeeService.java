package touk.demo.parkinglot.service.calculation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.calculator.FeeCalculator;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import touk.demo.parkinglot.repository.ParkingSpotRepository;

@AllArgsConstructor
@Service
public class FeeService implements CalculationService {

  @NonNull
  private final ParkingSpotRepository repository;

  @NonNull
  private final FeeCalculator calculator;

  @Override
  public CurrentFeeValue getCurrentFee(int id) throws InvalidSpotIdNumberException {
    return repository
        .findById(id)
        .filter(ParkingSpot::isOccupied)
        .map(calculator::apply)
        .orElseThrow(() -> new InvalidSpotIdNumberException("Invalid spot id number -- " + id));
  }
}
