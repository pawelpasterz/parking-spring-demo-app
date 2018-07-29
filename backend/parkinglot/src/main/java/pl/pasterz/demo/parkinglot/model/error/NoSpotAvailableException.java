package pl.pasterz.demo.parkinglot.model.error;

public class NoSpotAvailableException extends RuntimeException {

  public NoSpotAvailableException() {
  }

  public NoSpotAvailableException(String message) {
    super(message);
  }

  public NoSpotAvailableException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoSpotAvailableException(Throwable cause) {
    super(cause);
  }

  public NoSpotAvailableException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
