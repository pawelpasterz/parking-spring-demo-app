package touk.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import touk.demo.parkinglot.model.entity.Spot;

public class ParkingSpotInfo {

  @Getter
  private final Map<String, Integer> parkingStatus = new HashMap<>();

  @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
  private final Date created =  new Date();

  public void add(Spot spot) {
    parkingStatus.put(spot.getSpotStatus(), spot.getCountNumber());
  }

  @Override
  public String toString() {
    return "ParkingSpotInfo{" + "parkingStatus=" + parkingStatus + ", created=" + created + '}';
  }
}
