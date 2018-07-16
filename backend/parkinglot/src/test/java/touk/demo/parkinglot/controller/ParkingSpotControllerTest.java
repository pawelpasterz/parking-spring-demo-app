package touk.demo.parkinglot.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import touk.demo.parkinglot.model.dto.ClosingFeeValue;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.dto.ReservationConfirm;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumber;
import touk.demo.parkinglot.service.calculation.FeeService;
import touk.demo.parkinglot.service.info.InfoService;
import touk.demo.parkinglot.service.reservation.SpotReservationService;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ParkingSpotController.class)
class ParkingSpotControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private InfoService mService;

  @Mock
  private FeeService cService;

  @Mock
  private SpotReservationService rService;

  @InjectMocks
  private ParkingSpotController controller;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void shouldReturnStatusOK_getParkingSpotsInfo() throws Exception {
    ParkingSpotInfo info = new ParkingSpotInfo();
    info.getParkingStatus().put("free", 10);
    info.getParkingStatus().put("occupied", 10);

    when(mService.getParkingInfo()).thenReturn(info);

    MvcResult result = mockMvc
        .perform(get("/spots"))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains("free"));
    assertTrue(result.getResponse().getContentAsString().contains("occupied"));
  }

  @Test
  void shouldReturnStatusOK_getSpecifiedSpotsCount_free() throws Exception {
    String option = "free";

    FreeSpotInfo info = new FreeSpotInfo();
    info.setStatus(option);

    when(mService.getFreeSpotNumber()).thenReturn(info);

    MvcResult result = mockMvc
        .perform(get("/spots/info/" + option))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains(option));
  }

  @Test
  void shouldReturnStatusOK_getSpecifiedSpotsCount_occupied() throws Exception {
    String option = "occupied";

    OccupiedSpotInfo info = new OccupiedSpotInfo();
    info.setStatus(option);

    when(mService.getOccupiedSpotNumber()).thenReturn(info);

    MvcResult result = mockMvc
        .perform(get("/spots/info/" + option))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains(option));
  }

  @Test
  void shouldReturnBadRequest_getSpecifiedSpotsCount() throws Exception {
    String option = "bad_request";

    MvcResult result = mockMvc
        .perform(get("/spots/info/" + option))
        .andExpect(status().isBadRequest())
        .andReturn();

    assertEquals("Invalid option value", result.getResponse().getContentAsString());
  }

  @Test
  void shouldReturnStatusOK_requestFreeSpot() throws Exception {
    String driver = "VIP";
    String number = "AA1234";

    ReservationConfirm confirm = new ReservationConfirm();
    confirm.setDriverType(driver);

    when(rService.postSpotReservation(driver, number)).thenReturn(confirm);

    MvcResult result = mockMvc
        .perform(post("/spots?driver=" + driver + "&carNumber=" + number))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains(driver));
  }

  @Test
  void shouldReturnStatusOK_getSpotInfo() throws Exception {
    int id = 1;
    String carNumber = "AA1234";

    CurrentFeeValue value = new CurrentFeeValue();
    value.setCarNumber(carNumber);

    when(cService.getCurrentFee(id)).thenReturn(value);

    MvcResult result = mockMvc
        .perform(get("/spots/" + id))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains(carNumber));
  }

  @Test
  void shouldReturnStatusOK_closeReservation() throws Exception {
    int id = 1;
    ClosingFeeValue value = new ClosingFeeValue();
    value.setFee(12.36);

    CurrentFeeValue cValue = new CurrentFeeValue();

    when(cService.getCurrentFee(id)).thenReturn(cValue);
    when(rService.closeSpotReservation(id, cValue)).thenReturn(value);

    MvcResult result = mockMvc
        .perform(post("/spots/" + id))
        .andExpect(status().isOk())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains("12.36"));
  }

  @Test
  void shouldReturnStatusBadRequest_closeReservation() throws Exception {
    int id = 1;

    when(cService.getCurrentFee(id)).thenReturn(InvalidSpotIdNumber.getInstance());

    MvcResult result = mockMvc
        .perform(post("/spots/" + id))
        .andExpect(status().isBadRequest())
        .andReturn();

    assertTrue(result.getResponse().getContentAsString().contains("Invalid"));
  }
}