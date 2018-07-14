package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class OccupiedSpotInfo {

  private String status;
  private int number;

  @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  private final Date created;

  public OccupiedSpotInfo() {
    created = new Date();
  }

  public String getStatus() {
    return status;
  }

  public int getNumber() {
    return number;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "OccupiedSpotInfo{"
        + "status='"
        + status
        + '\''
        + ", number="
        + number
        + ", created="
        + created
        + '}';
  }
}
