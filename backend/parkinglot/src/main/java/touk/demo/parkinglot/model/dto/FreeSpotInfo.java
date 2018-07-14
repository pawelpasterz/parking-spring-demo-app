package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class FreeSpotInfo {

  private String status;
  private int number;

  @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  private final Date created;

  public FreeSpotInfo() {
    created = new Date();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public String toString() {
    return "FreeSpotInfo{"
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
