package touk.demo.parkinglot.calculator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.FeeValue;
import touk.demo.parkinglot.model.entity.DriverType;
import touk.demo.parkinglot.model.entity.ParkingSpot;

@Component
public class FeeCalculator implements Calculator<ParkingSpot, FeeValue> {

  public FeeValue getFeeValue(ParkingSpot spot) {
    FeeValue feeValue = new FeeValue();

    DriverType driver = spot.getDriverType();
    LocalDate startDate = spot.getStartDate();
    feeValue.setStartTime(startDate);
    LocalDate currentDate = LocalDate.now();
    feeValue.setCarNumber(spot.getCarNumber());

    long minutes = ChronoUnit.MINUTES.between(startDate, currentDate) % 60;

    feeValue.setMinutesTillNextHour((int) minutes);
    feeValue.setFee(calculateFee(minutes, driver));

    return feeValue;
  }

  private double calculateFee(long minutes, DriverType driver) {
    double fee = 0;
    int hours = (int) minutes / 60;

    // Two major cases, when spot was occupied for x hour(s) and:
    // 1) 0 seconds should be x hour
    // 2) seconds != 0 should be x + 1 hour
    if ((int) minutes / 60 != 0) {
      hours++;
    }

    if (hours > 0) {
      fee += (double) driver.getFirstHour();
    }

    if (hours > 1) {
      fee += (double) driver.getSecondHour();
    }

    if (hours > 2) {
      double previous = (double) driver.getSecondHour();

      while (hours > 2) {
        double current = previous * driver.getThirdHour();
        fee += current;
        previous = current;
        hours--;
      }
    }

    return fee;
  }
}
