package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.response.ServiceResponse;

public class ReservationConfirm implements ServiceResponse {

  private String driverType;
  private int spotId;

  @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  private Date beginTime;

  public String getDriverType() {
    return driverType;
  }

  public int getSpotId() {
    return spotId;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setDriverType(String driverType) {
    this.driverType = driverType;
  }

  public void setSpotId(int spotId) {
    this.spotId = spotId;
  }

  public void setBeginTime(Date beginTime) {
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
