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

  private final FeeCalculator calculator = new FeeCalculator();

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

  @Test
  void shouldReturnFeeValueEqualTwo() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[0]);
    spot.setStartDate(new Date(System.currentTimeMillis() - (3700 * 1000)));

    assertEquals(2d, calculator.getFeeValue(spot).getFee());
  }

  @Test
  void shouldReturnFeeValueEqualFour_Four() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[0]);
    spot.setStartDate(new Date(System.currentTimeMillis()
        - ((2 * 3600 + 100) * 1000)));

    assertEquals(4.4, calculator.getFeeValue(spot).getFee());
  }

  @Test
  void shouldReturnFeeValueEqualThree() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[1]);
    spot.setStartDate(new Date(System.currentTimeMillis()
        - ((1 * 3600 + 100) * 1000)));

    assertEquals(3d, calculator.getFeeValue(spot).getFee());
  }

  @Test
  void shouldReturnFeeValueEqualSix() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[1]);
    spot.setStartDate(new Date(System.currentTimeMillis()
        - ((2 * 3600 + 100) * 1000)));

    assertEquals(6d, calculator.getFeeValue(spot).getFee());
  }

  @Test
  void shouldReturnFeeValueEqualThreeRegular() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[1]);
    spot.setStartDate(new Date(System.currentTimeMillis()
        - ((2 * 3600 + 0) * 1000)));

    assertEquals(3d, calculator.getFeeValue(spot).getFee());
  }

  @Test
  void shouldReturnFeeValueEqualOne_Seven_Two_FiveRegular() {
    ParkingSpot spot = new ParkingSpot();
    spot.setCarNumber("test");
    spot.setDriverType(driver[1]);
    spot.setStartDate(new Date(System.currentTimeMillis()
        - ((4 * 3600 + 100) * 1000)));

    assertEquals(17.25, calculator.getFeeValue(spot).getFee());
  }
}