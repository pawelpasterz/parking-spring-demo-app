package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.response.ServiceResponse;

public class FeeValue implements ServiceResponse {

  private int spotId;
  private String carNumber;
  private double fee;
  private int minutesTillNextHour;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private LocalDate startTime;

  public int getSpotId() {
    return spotId;
  }

  public void setSpotId(int spotId) {
    this.spotId = spotId;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }

  public double getFee() {
    return fee;
  }

  public void setFee(double fee) {
    this.fee = fee;
  }

  public LocalDate getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDate startTime) {
    this.startTime = startTime;
  }

  public int getMinutesTillNextHour() {
    return minutesTillNextHour;
  }

  public void setMinutesTillNextHour(int minutesTillNextHour) {
    this.minutesTillNextHour = minutesTillNextHour;
  }
}
