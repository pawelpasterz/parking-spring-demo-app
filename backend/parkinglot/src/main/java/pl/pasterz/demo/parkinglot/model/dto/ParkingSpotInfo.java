package pl.pasterz.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import pl.pasterz.demo.parkinglot.model.entity.Spot;

public class ParkingSpotInfo {

  @Getter
  private final Map<String, Integer> parkingStatus = new HashMap<>();

  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MMM-dd HH:mm:ss ")
  @DateTimeFormat(iso = ISO.DATE_TIME)
  private final Date created =  new Date();

  public void add(Spot spot) {
    parkingStatus.put(spot.getSpotStatus(), spot.getCountNumber());
  }

  @Override
  public String toString() {
    return "ParkingSpotInfo{" + "parkingStatus=" + parkingStatus + ", created=" + created + '}';
  }
}
