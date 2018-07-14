package touk.demo.parkinglot.converter;

import java.util.List;
import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

@Component
public class SpotToParkingSpotInfoConverter implements BaseConverter<List<Spot>, ParkingSpotInfo> {

  @Override
  public ParkingSpotInfo convert(List<Spot> from) {
    ParkingSpotInfo info = new ParkingSpotInfo();
    from.forEach(info::add);
    return info;
  }
}
