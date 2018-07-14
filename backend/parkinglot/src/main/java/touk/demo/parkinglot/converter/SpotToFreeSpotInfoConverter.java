package touk.demo.parkinglot.converter;

import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

@Component
public class SpotToFreeSpotInfoConverter implements BaseConverter<Spot, FreeSpotInfo> {

  @Override
  public FreeSpotInfo convert(Spot from) {
    FreeSpotInfo info = new FreeSpotInfo();
    info.setStatus(from.getSpotStatus());
    info.setNumber(from.getCountNumber());

    return info;
  }
}
