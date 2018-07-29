package pl.pasterz.demo.parkinglot.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.pasterz.demo.parkinglot.model.dto.CurrentFeeValue;
import pl.pasterz.demo.parkinglot.model.dto.ClosingFeeValue;
import pl.pasterz.demo.parkinglot.model.dto.ParkingSpotInfo;
import pl.pasterz.demo.parkinglot.model.dto.ReservationConfirm;
import pl.pasterz.demo.parkinglot.model.dto.SpotInfo;
import pl.pasterz.demo.parkinglot.service.info.ManagementService;
import pl.pasterz.demo.parkinglot.service.calculation.CalculationService;
import pl.pasterz.demo.parkinglot.service.reservation.ReservationService;

@AllArgsConstructor
@RestController
@RequestMapping("/spots")
public class ParkingSpotController {

  @NonNull
  private final ManagementService mService;

  @NonNull
  private final CalculationService cService;

  @NonNull
  private final ReservationService rService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ParkingSpotInfo getParkingSpotsInfo() {
    return mService.getParkingInfo();
  }

  @GetMapping("/info/{option}")
  @ResponseStatus(HttpStatus.OK)
  public SpotInfo getSpecifiedSpotsCount(@PathVariable(value = "option") String option) {
    return mService.getOptionSpotInfo(option);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReservationConfirm requestFreeSpot(
      @RequestParam(name = "driver") String driver,
      @RequestParam(name = "carNumber") String carNumber
  ) {
    return rService.postSpotReservation(driver, carNumber);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CurrentFeeValue getSpotInfo(@PathVariable(name = "id") int id) {
    return cService.getCurrentFee(id);
  }

  @PostMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ClosingFeeValue closeReservation(@PathVariable(name = "id") int id) {
    return rService.closeSpotReservation(id, cService.getCurrentFee(id));
  }
}
