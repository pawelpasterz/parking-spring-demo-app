package touk.demo.parkinglot.converter;

import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.entity.Spots;

@Component
public class SpotToFreeSpotInfoConverter implements BaseConverter<Spots, FreeSpotInfo> {

  @Override
  public FreeSpotInfo convert(Spots from) {
    FreeSpotInfo info = new FreeSpotInfo();
    info.setStatus(from.getSpotStatus());
    info.setNumber(from.getCountNumber());

    return info;
  }
}
