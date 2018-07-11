package touk.demo.parkinglot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import touk.demo.parkinglot.service.ManagementService;

@RestController
@RequestMapping("/spots")
public class ParkingSpotController {

  private ManagementService service;

  @Autowired
  public ParkingSpotController(ManagementService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity getParkingSpotsInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(service.getParkingInfo());
  }
}
