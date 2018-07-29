package pl.pasterz.demo.parkinglot.model.error;

import lombok.Getter;

@Getter
public final class InvalidSpotIdNumber {

  private final int status;
  private final String message;
  private final long timeStamp;

  public InvalidSpotIdNumber(int status, String message, long timeStamp) {
    this.status = status;
    this.message = message;
    this.timeStamp = timeStamp;
  }
}
