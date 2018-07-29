package pl.pasterz.demo.parkinglot.converter;

import java.util.List;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import pl.pasterz.demo.parkinglot.model.dto.ParkingSpotInfo;
import pl.pasterz.demo.parkinglot.model.entity.Spot;

@Component
public class SpotToParkingSpotInfoConverter implements Function<List<Spot>, ParkingSpotInfo> {

  @Override
  public ParkingSpotInfo apply(List<Spot> spots) {
    ParkingSpotInfo info = new ParkingSpotInfo();
    spots.forEach(info::add);
    return info;
  }
}
