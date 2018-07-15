package touk.demo.parkinglot.calculator;

import java.util.Date;
import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.entity.DriverType;
import touk.demo.parkinglot.model.entity.ParkingSpot;

@Component
public class FeeCalculator implements Calculator<ParkingSpot, CurrentFeeValue> {

  public CurrentFeeValue getFeeValue(ParkingSpot spot) {
    CurrentFeeValue currentFeeValue = new CurrentFeeValue();

    DriverType driver = spot.getDriverType();
    Date startDate = spot.getStartDate();
    currentFeeValue.setStartTime(startDate);
    Date currentDate = new Date();
    currentFeeValue.setCarNumber(spot.getCarNumber());

    int minutes = (int) ((currentDate.getTime() - startDate.getTime()) / 1000 / 60);

    currentFeeValue.setMinutesTillNextHour(60 - minutes % 60);
    currentFeeValue.setFee(calculateFee(minutes, driver));

    return currentFeeValue;
  }

  private double calculateFee(int minutes, DriverType driver) {
    double fee = 0;
    int hours = minutes / 60;

    // Two major cases, when reservation was occupied for x hour(s) and:
    // 1) 0 seconds should be x hour
    // 2) seconds != 0 should be x + 1 hour
    if (minutes % 60 != 0 || minutes == 0) {
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
