package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.entity.Spot;

public class ParkingSpotInfo {

  private final Map<String, Integer> parkingStatus;

  @JsonFormat(pattern = "dd-MM-yyyy")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private final LocalDate created;

  public ParkingSpotInfo() {
    parkingStatus = new HashMap<>();
    created = LocalDate.now();
  }

  public void add(Spot spot) {
    parkingStatus.put(spot.getSpotStatus(), spot.getCountNumber());
  }

  public Map<String, Integer> getParkingStatus() {
    return parkingStatus;
  }

  public LocalDate getCreated() {
    return created;
  }

  @Override
  public String toString() {
    return "ParkingSpotInfo{" + "parkingStatus=" + parkingStatus + ", created=" + created + '}';
  }
}
