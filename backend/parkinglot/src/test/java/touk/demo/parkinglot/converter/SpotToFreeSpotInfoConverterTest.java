package touk.demo.parkinglot.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

class SpotToFreeSpotInfoConverterTest {

  private final SpotToFreeSpotInfoConverter converter = new SpotToFreeSpotInfoConverter();
  private final Spot spot = new Spot("free", 1234);

  @Test
  void shouldReturnFreeSpotInfoObject() {
    assertEquals(FreeSpotInfo.class, converter.convert(spot).getClass());
  }

  @Test
  void shouldReturnOccupied() {
    assertEquals("free", converter.convert(spot).getStatus());
  }

  @Test
  void shouldReturn1234() {
    assertEquals(1234, converter.convert(spot).getNumber());
  }
}
