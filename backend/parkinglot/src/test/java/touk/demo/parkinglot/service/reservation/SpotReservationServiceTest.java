package touk.demo.parkinglot.service.reservation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import touk.demo.parkinglot.converter.ParkingSpotToReservationConfirmConverter;
import touk.demo.parkinglot.model.dto.ClosingFeeValue;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.entity.DriverType;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.entity.Spot;
import touk.demo.parkinglot.model.error.NoSpotAvailableException;
import touk.demo.parkinglot.repository.DriverTypeRepository;
import touk.demo.parkinglot.repository.ParkingSpotRepository;
import touk.demo.parkinglot.repository.SpotsRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class SpotReservationServiceTest {

  @Mock
  private DriverTypeRepository driverTypeRepository;

  @Mock
  private SpotsRepository spotsRepository;

  @Mock
  private ParkingSpotRepository parkingSpotRepository;

  @Mock
  private ParkingSpotToReservationConfirmConverter reservationConfirmConverter;

  @InjectMocks
  private SpotReservationService service;


  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void shouldReturnConfirmObjectWhenPostingNewObjectToDatabase() {
    setMocksForPost();

    assertEquals(
        ReservationConfirm.class,
        service
            .postSpotReservation("VIP", "test")
            .getClass()
    );
  }

  @Test
  void shouldReturnVIPConfirmObject() {
    setMocksForPost();

    ReservationConfirm check = (ReservationConfirm) service.postSpotReservation("VIP", "test");

    assertEquals(1, check.getSpotId());
    assertEquals("VIP", check.getDriverType());
  }

  @Test
  void shouldThrowInvalidSpotIdObjectWithMessage() {
    setMocksForPost();

    Spot spot = new Spot("free", 0);
    Optional<Spot> spotOpt = Optional.of(spot);
    when(spotsRepository.findById(any(String.class)))
        .thenReturn(spotOpt);

    assertThrows(NoSpotAvailableException.class, () -> service.postSpotReservation("VIP", "test"));
  }

  @Test
  void shouldReturnClosingFeeValueObject() {
    setMocksForDelete();

    CurrentFeeValue value = new CurrentFeeValue();
    value.setSpotId(1);
    value.setFee(2.5);

    assertEquals(
        ClosingFeeValue.class,
        service
            .closeSpotReservation(1, value)
            .getClass()
    );
  }

  private void setMocksForPost() {
    Spot spot = new Spot("free", 11);
    Optional<Spot> spotOpt = Optional.of(spot);
    when(spotsRepository.findById("free"))
        .thenReturn(spotOpt);

    DriverType driverType = new DriverType("VIP", 0, 2, 1.2);
    Optional<DriverType> driverOpt = Optional.of(driverType);
    when(driverTypeRepository.findById(any(String.class)))
        .thenReturn(driverOpt);

    ParkingSpot parkingSpot = new ParkingSpot(false, null, null, null);
    parkingSpot.setId(1);
    Stream<ParkingSpot> parkingSpotStr = Stream.of(parkingSpot);
    when(parkingSpotRepository.findAllByOccupiedOrderByIdAsc(false))
        .thenReturn(parkingSpotStr);

    Spot spot2 = new Spot("occupied", 10);
    Optional<Spot> spotOpt2 = Optional.of(spot2);
    when(spotsRepository.findById("occupied"))
        .thenReturn(spotOpt2);

    ParkingSpot parkingSpot2 = new ParkingSpot(true, "test", new Date(), driverType);
    parkingSpot2.setId(1);
    when(parkingSpotRepository.save(any(ParkingSpot.class)))
        .thenReturn(parkingSpot2);

    ReservationConfirm confirm = new ReservationConfirm();
    confirm.setSpotId(1);
    confirm.setDriverType("VIP");
    when(reservationConfirmConverter.apply(any(ParkingSpot.class)))
        .thenReturn(confirm);
  }

  private void setMocksForDelete() {
    Spot spotFree = new Spot("free", 10);
    Spot spotOccupied = new Spot("occupied", 10);

    when(spotsRepository.findById("free"))
        .thenReturn(Optional.of(spotFree));

    when(spotsRepository.findById("occupied"))
        .thenReturn(Optional.of(spotOccupied));
  }
}