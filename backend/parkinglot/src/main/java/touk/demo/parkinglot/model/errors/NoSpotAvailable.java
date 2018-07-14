package touk.demo.parkinglot.model.errors;

import touk.demo.parkinglot.model.interfaces.ParkingServiceResponse;

public final class NoSpotAvailable implements ParkingServiceResponse {

  private static class NoSpotAvailableLoader {
    private static final NoSpotAvailable INSTANCE = new NoSpotAvailable();
  }

  private final String message = "No parking spots available";

  private NoSpotAvailable() {
    if (NoSpotAvailableLoader.INSTANCE != null) {
      throw new IllegalStateException("Already instantiated");
    }
  }

  public static NoSpotAvailable getInstance() {
    return NoSpotAvailableLoader.INSTANCE;
  }

  public String getMessage() {
    return message;
  }
}