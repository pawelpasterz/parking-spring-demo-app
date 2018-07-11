package touk.demo.parkinglot.converter;

import java.util.List;
import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.entity.Spots;

@Component
public class SpotToParkingSpotInfoConverter implements BaseConverter<List<Spots>, ParkingSpotInfo> {

  @Override
  public ParkingSpotInfo convert(List<Spots> from) {
    ParkingSpotInfo info = new ParkingSpotInfo();
    from.forEach(info::add);
    return info;
  }
}
