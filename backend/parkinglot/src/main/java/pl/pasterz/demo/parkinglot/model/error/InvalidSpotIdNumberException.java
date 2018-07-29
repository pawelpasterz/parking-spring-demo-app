package pl.pasterz.demo.parkinglot.model.error;

public class InvalidSpotIdNumberException extends RuntimeException {

  public InvalidSpotIdNumberException() {
    super();
  }

  public InvalidSpotIdNumberException(String message) {
    super(message);
  }

  public InvalidSpotIdNumberException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidSpotIdNumberException(Throwable cause) {
    super(cause);
  }

  protected InvalidSpotIdNumberException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
