package touk.demo.parkinglot.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

class SpotToOccupiedInfoConverterTest {

  private final SpotToOccupiedInfoConverter converter = new SpotToOccupiedInfoConverter();
  private final Spot spot = new Spot("occupied", 1234);

  @Test
  void shouldReturnFreeSpotInfoObject() {
    assertEquals(OccupiedSpotInfo.class, converter.convert(spot).getClass());
  }

  @Test
  void shouldReturnOccupied() {
    assertEquals("occupied", converter.convert(spot).getStatus());
  }

  @Test
  void shouldReturn1234() {
    assertEquals(1234, converter.convert(spot).getNumber());
  }
}