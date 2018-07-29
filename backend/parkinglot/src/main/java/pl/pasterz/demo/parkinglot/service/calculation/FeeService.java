package pl.pasterz.demo.parkinglot.service.calculation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.pasterz.demo.parkinglot.model.dto.CurrentFeeValue;
import pl.pasterz.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import pl.pasterz.demo.parkinglot.repository.ParkingSpotRepository;
import pl.pasterz.demo.parkinglot.calculator.FeeCalculator;
import pl.pasterz.demo.parkinglot.model.entity.ParkingSpot;

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
