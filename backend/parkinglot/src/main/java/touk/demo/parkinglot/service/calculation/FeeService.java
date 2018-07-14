package touk.demo.parkinglot.service.calculation;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.calculator.Calculator;
import touk.demo.parkinglot.model.dto.FeeValue;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumber;
import touk.demo.parkinglot.model.response.ServiceResponse;
import touk.demo.parkinglot.repository.ParkingSpotRepository;

@Service
public class FeeService implements CalculationService {

  private final ParkingSpotRepository repository;
  private final Calculator<ParkingSpot, FeeValue> calculator;

  @Autowired
  public FeeService(ParkingSpotRepository repository,
      Calculator<ParkingSpot, FeeValue> calculator) {
    this.repository = repository;
    this.calculator = calculator;
  }

  @Override
  public ServiceResponse getCurrentFee(int id) {
    Optional<ParkingSpot> spotOpt = repository.findById(id);

    if (!spotOpt.isPresent() || !spotOpt.get().isOccupied()) {
      return InvalidSpotIdNumber.getInstance();
    }

    return calculator.getFeeValue(spotOpt.get());
  }

}
