package pl.pasterz.demo.parkinglot.service.calculation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import pl.pasterz.demo.parkinglot.model.dto.CurrentFeeValue;
import pl.pasterz.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import pl.pasterz.demo.parkinglot.repository.ParkingSpotRepository;
import pl.pasterz.demo.parkinglot.calculator.FeeCalculator;
import pl.pasterz.demo.parkinglot.model.entity.ParkingSpot;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class FeeServiceTest {

  @Mock
  private ParkingSpotRepository repository;

  @Mock
  private FeeCalculator calculator;

  @InjectMocks
  private FeeService service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void shouldReturnInvalidSpotIdNumberObjectWhenInvalidId() {
    when(repository.findById(any(Integer.class)))
        .thenReturn(Optional.empty());

    assertThrows(InvalidSpotIdNumberException.class, () -> service.getCurrentFee(1));
  }

  @Test
  void shouldReturnInvalidSpotIdNumberObjectWhenOccupied() {
    when(repository.findById(any(Integer.class)))
        .thenReturn(Optional.of(new ParkingSpot(false, null, null, null)));

    assertThrows(InvalidSpotIdNumberException.class, () -> service.getCurrentFee(1));
  }

  @Test
  void shouldReturnCurrentFeeValueObject() throws InvalidSpotIdNumberException {
    ParkingSpot spot = new ParkingSpot(true, null, null, null);

    when(repository.findById(any(Integer.class)))
        .thenReturn(Optional.of(spot));

    CurrentFeeValue value = new CurrentFeeValue();
    value.setFee(2.5);
    value.setSpotId(1);

    when(calculator.apply(any(ParkingSpot.class)))
        .thenReturn(value);

    CurrentFeeValue check = (CurrentFeeValue)service.getCurrentFee(1);

    assertEquals(CurrentFeeValue.class, check.getClass());
    assertEquals(2.5, check.getFee());
    assertEquals(1, check.getSpotId());
  }
}