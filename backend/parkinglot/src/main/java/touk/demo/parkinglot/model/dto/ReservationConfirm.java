package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.response.ServiceResponse;

public class ReservationConfirm implements ServiceResponse {

  private String driverType;
  private int spotId;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private LocalDate beginTime;

  public String getDriverType() {
    return driverType;
  }

  public int getSpotId() {
    return spotId;
  }

  public LocalDate getBeginTime() {
    return beginTime;
  }

  public void setDriverType(String driverType) {
    this.driverType = driverType;
  }

  public void setSpotId(int spotId) {
    this.spotId = spotId;
  }

  public void setBeginTime(LocalDate beginTime) {
    this.beginTime = beginTime;
  }

  @Override
  public String toString() {
    return "ReservationConfirm{"
        + "driverType='"
        + driverType
        + '\''
        + ", spotId="
        + spotId
        + ", beginTime="
        + beginTime
        + '}';
  }
}
