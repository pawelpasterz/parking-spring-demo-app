package touk.demo.parkinglot.converter;

import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

@Component
public class SpotToOccupiedInfoConverter implements BaseConverter<Spot, OccupiedSpotInfo> {

  @Override
  public OccupiedSpotInfo convert(Spot from) {
    OccupiedSpotInfo info = new OccupiedSpotInfo();
    info.setStatus(from.getSpotStatus());
    info.setNumber(from.getCountNumber());

    return info;
  }
}
