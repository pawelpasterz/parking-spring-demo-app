package touk.demo.parkinglot.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;

class SpotToParkingSpotInfoConverterTest {

  final private SpotToParkingSpotInfoConverter converter =
      new SpotToParkingSpotInfoConverter();

  final private Spot[] spotArr = {
      new Spot("occupied", 10),
      new Spot("free", 20)
  };

  final private List<Spot> spots = Arrays.asList(spotArr);

  @Test
  void shouldReturnParkingSpotInfoObject() {
    assertEquals(ParkingSpotInfo.class, converter.apply(spots).getClass());
  }

  @Test
  void shouldReturnMapSizeEqualsTwo() {
    assertEquals(2, converter.apply(spots).getParkingStatus().size());
  }

  @Test
  void shouldReturnMapWithKeysOccupiedFree() {
    assertTrue(converter.apply(spots).getParkingStatus().containsKey("occupied"));
    assertTrue(converter.apply(spots).getParkingStatus().containsKey("free"));
  }

  @Test
  void shouldReturnValue10WithKeyOccupied() {
    assertEquals(Integer.valueOf(10), converter.apply(spots).getParkingStatus().get("occupied"));
  }
}