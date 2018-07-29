package pl.pasterz.demo.parkinglot.model.error;

public class InvalidOptionRequestException extends RuntimeException {

  public InvalidOptionRequestException() {
  }

  public InvalidOptionRequestException(String message) {
    super(message);
  }

  public InvalidOptionRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidOptionRequestException(Throwable cause) {
    super(cause);
  }

  public InvalidOptionRequestException(
      String message,
      Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace
  ) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
