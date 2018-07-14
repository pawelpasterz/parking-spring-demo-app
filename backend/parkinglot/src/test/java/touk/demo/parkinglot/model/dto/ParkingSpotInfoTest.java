package touk.demo.parkinglot.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.entity.Spot;

class ParkingSpotInfoTest {

  final private ParkingSpotInfo info = new ParkingSpotInfo();

  @Test
  void shouldReturnSameValue() {
    Spot spot = new Spot();
    spot.setCountNumber(1);
    spot.setSpotStatus("occupied");
    info.add(spot);

    assertEquals(info.getParkingStatus().get("occupied"), Integer.valueOf(1));
  }
}