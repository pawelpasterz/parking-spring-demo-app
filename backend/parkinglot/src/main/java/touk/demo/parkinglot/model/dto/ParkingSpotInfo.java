package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.entity.Spots;

public class ParkingSpotInfo {

  final private Map<String, Integer> parkingStatus;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  final private Date created;

  public ParkingSpotInfo() {
    parkingStatus = new HashMap<>();
    created = new Date();
  }

  public void add(Spots spots) {
    parkingStatus.put(spots.getSpotStatus(), spots.getCountNumber());
  }

  public Map<String, Integer> getParkingStatus() {
    return parkingStatus;
  }

  public Date getCreated() {
    return created;
  }

  @Override
  public String toString() {
    return "ParkingSpotInfo{" +
        "parkingStatus=" + parkingStatus +
        ", created=" + created +
        '}';
  }
}
