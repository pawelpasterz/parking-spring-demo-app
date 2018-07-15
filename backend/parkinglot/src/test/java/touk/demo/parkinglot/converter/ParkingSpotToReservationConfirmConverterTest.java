package touk.demo.parkinglot.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.entity.DriverType;
import touk.demo.parkinglot.model.entity.ParkingSpot;

class ParkingSpotToReservationConfirmConverterTest {

  private final ParkingSpotToReservationConfirmConverter converter =
      new ParkingSpotToReservationConfirmConverter();

  private DriverType driver = new DriverType("VIP", 0, 2, 1.2);

  private final ParkingSpot spot = new ParkingSpot(true, "1234", new Date(), driver);

  @Test
  void shouldReturnReservationConfirmObject() {
    spot.setId(1);
    assertEquals(ReservationConfirm.class, converter.convert(spot).getClass());
  }

  @Test
  void shouldReturnReservationConfirmValidBeginTime() {
    Date now = new Date();
    spot.setStartDate(now);
    spot.setId(1);

    assertEquals(now, converter.convert(spot).getBeginTime());
  }

  @Test
  void shouldReturnReservationConfirmValidSpotId() {
    spot.setId(6);

    assertEquals(6, converter.convert(spot).getSpotId());
  }

  @Test
  void shouldReturnReservationConfirmValidDriverType() {
    assertEquals("VIP", converter.convert(spot).getDriverType());
  }
}
