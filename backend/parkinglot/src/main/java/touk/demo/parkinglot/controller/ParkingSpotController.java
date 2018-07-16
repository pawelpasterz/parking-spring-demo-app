package touk.demo.parkinglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import touk.demo.parkinglot.model.dto.CurrentFeeValue;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumber;
import touk.demo.parkinglot.model.response.ServiceResponse;
import touk.demo.parkinglot.service.info.ManagementService;
import touk.demo.parkinglot.service.calculation.CalculationService;
import touk.demo.parkinglot.service.reservation.ReservationService;

@RestController
@RequestMapping("/spots")
public class ParkingSpotController {

  private ManagementService mService;
  private CalculationService cService;
  private ReservationService rService;

  @Autowired
  public ParkingSpotController(ManagementService mService,
      CalculationService cService,
      ReservationService rService) {
    this.mService = mService;
    this.cService = cService;
    this.rService = rService;
  }

  @GetMapping
  public ResponseEntity getParkingSpotsInfo() {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(mService.getParkingInfo());
  }

  @GetMapping("/info/{option}")
  public ResponseEntity getSpecifiedSpotsCount(
      @PathVariable(value = "option") String option) {
    ResponseEntity response;

    switch (option) {
      case "free":
        response = ResponseEntity
            .status(HttpStatus.OK)
            .body(mService.getFreeSpotNumber());
        break;
      case "occupied":
        response = ResponseEntity
            .status(HttpStatus.OK)
            .body(mService.getOccupiedSpotNumber());
        break;
      default:
        response = ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Invalid option value");
    }

    return response;
  }

  @PostMapping
  public ResponseEntity requestFreeSpot(
      @RequestParam(name = "driver") String driver,
      @RequestParam(name = "carNumber") String carNumber) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(rService.postSpotReservation(driver, carNumber));
  }

  @GetMapping("/{id}")
  public ResponseEntity getSpotInfo(@PathVariable(name = "id") int id) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(cService.getCurrentFee(id));
  }

  @PostMapping("/{id}")
  public ResponseEntity closeReservation(@PathVariable(name = "id") int id) {
    ServiceResponse response = cService.getCurrentFee(id);
    if (response instanceof InvalidSpotIdNumber) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body(response);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(rService.closeSpotReservation(id, (CurrentFeeValue)response));
  }
}
