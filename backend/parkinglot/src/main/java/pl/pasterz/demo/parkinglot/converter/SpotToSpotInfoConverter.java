package pl.pasterz.demo.parkinglot.converter;

import java.util.Date;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import pl.pasterz.demo.parkinglot.model.dto.SpotInfo;
import pl.pasterz.demo.parkinglot.model.entity.Spot;

@Component
public class SpotToSpotInfoConverter implements Function<Spot, SpotInfo> {

  @Override
  public SpotInfo apply(Spot from) {
    return new SpotInfo(from.getSpotStatus(), from.getCountNumber(), new Date());
  }
}
