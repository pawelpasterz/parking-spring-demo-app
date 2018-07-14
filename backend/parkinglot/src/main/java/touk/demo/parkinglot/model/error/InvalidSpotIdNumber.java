package touk.demo.parkinglot.model.error;

import touk.demo.parkinglot.model.response.ServiceResponse;

public final class InvalidSpotIdNumber implements ServiceResponse {

  private static class InvalidSpotIdNumberLoader {
    private static final InvalidSpotIdNumber INSTANCE = new InvalidSpotIdNumber();
  }

  private final String message = "Invalid spot id number";

  private InvalidSpotIdNumber() {
    if (InvalidSpotIdNumberLoader.INSTANCE != null) {
      throw new IllegalStateException("Already instantiated");
    }
  }

  public static InvalidSpotIdNumber getInstance() {
    return InvalidSpotIdNumberLoader.INSTANCE;
  }

  public String getMessage() {
    return message;
  }
}
