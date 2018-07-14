package touk.demo.parkinglot.model.error;

import touk.demo.parkinglot.model.response.ServiceResponse;

public final class NoSpotAvailable implements ServiceResponse {

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
