package touk.demo.parkinglot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import touk.demo.parkinglot.model.error.InvalidOptionRequest;
import touk.demo.parkinglot.model.error.InvalidOptionRequestException;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumber;
import touk.demo.parkinglot.model.error.InvalidSpotIdNumberException;
import touk.demo.parkinglot.model.error.NoSpotAvailable;
import touk.demo.parkinglot.model.error.NoSpotAvailableException;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<InvalidSpotIdNumber> invalidSpotIdNumber(InvalidSpotIdNumberException ex) {
    return ResponseEntity
        .badRequest()
        .body(new InvalidSpotIdNumber(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        ));
  }

  @ExceptionHandler
  public ResponseEntity<NoSpotAvailable> noSpotAvailable(NoSpotAvailableException ex) {
    return ResponseEntity
        .ok()
        .body(new NoSpotAvailable(
            HttpStatus.OK.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        ));
  }

  @ExceptionHandler
  public ResponseEntity<InvalidOptionRequest> invalidOptionRequest(InvalidOptionRequestException ex) {
    return ResponseEntity
        .badRequest()
        .body(new InvalidOptionRequest(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        ));
  }
}
