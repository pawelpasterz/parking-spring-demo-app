package touk.demo.parkinglot.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.entity.DriverType;
import touk.demo.parkinglot.model.entity.ParkingSpot;

class FeeCalculatorTest {

  private DriverType[] driver = {
      new DriverType("VIP", 0, 2, 1.2),
        new DriverType("regular", 1, 2, 1.5)
    };

  private FeeCalculator calculator = new FeeCalculator();

  @Test
  void shouldReturnFeeValueEqualZero() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[0]);
    spot.setStartDate(new Date());

    assertEquals(0d, calculator.getFeeValue(spot).getFee());
  }

  @Test
  void shouldReturnFeeValueEqualOne() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[1]);
    spot.setStartDate(new Date());

    assertEquals(1d, calculator.getFeeValue(spot).getFee());
  }
}