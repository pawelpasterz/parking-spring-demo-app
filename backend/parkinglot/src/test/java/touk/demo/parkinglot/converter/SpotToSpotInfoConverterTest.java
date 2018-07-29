package touk.demo.parkinglot.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.dto.SpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

class SpotToSpotInfoConverterTest {

  private final SpotToSpotInfoConverter converter = new SpotToSpotInfoConverter();
  private final Spot spot = new Spot("free", 1234);

  @Test
  void shouldReturnFreeSpotInfoObject() {
    assertEquals(SpotInfo.class,
        converter
            .apply(spot)
            .getClass()
    );
  }

  @Test
  void shouldReturnOccupied() {
    assertEquals("free",
        converter
            .apply(spot)
            .getStatus()
    );
  }

  @Test
  void shouldReturn1234() {
    assertEquals(1234,
        converter
            .apply(spot)
            .getNumber()
    );
  }
}
