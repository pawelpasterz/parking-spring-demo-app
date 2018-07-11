package touk.demo.parkinglot.model.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.entity.Spots;

class ParkingSpotInfoTest {

  final private ParkingSpotInfo info = new ParkingSpotInfo();

  @Test
  void shouldReturnSameValue() {
    Spots spots = new Spots();
    spots.setCountNumber(1);
    spots.setSpotStatus("occupied");
    info.add(spots);

    assertEquals(info.getParkingStatus().get("occupied"), Integer.valueOf(1));
  }
}