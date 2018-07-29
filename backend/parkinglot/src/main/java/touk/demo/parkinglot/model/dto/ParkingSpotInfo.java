package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.entity.Spot;

public class ParkingSpotInfo {

  private final Map<String, Integer> parkingStatus;

  @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  private final Date created;

  public ParkingSpotInfo() {
    parkingStatus = new HashMap<>();
    created = new Date();
  }

  public void add(Spot spot) {
    parkingStatus.put(spot.getSpotStatus(), spot.getCountNumber());
  }

  public Map<String, Integer> getParkingStatus() {
    return parkingStatus;
  }

  @Override
  public String toString() {
    return "ParkingSpotInfo{" + "parkingStatus=" + parkingStatus + ", created=" + created + '}';
  }
}
