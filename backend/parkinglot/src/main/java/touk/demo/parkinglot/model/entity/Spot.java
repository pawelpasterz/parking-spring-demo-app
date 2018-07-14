package touk.demo.parkinglot.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spots")
public class Spot {

  @Id
  private String spotStatus;
  private int countNumber;

  public Spot() {}

  public Spot(String spotStatus, int countNumber) {
    this.spotStatus = spotStatus;
    this.countNumber = countNumber;
  }

  public String getSpotStatus() {
    return spotStatus;
  }

  public int getCountNumber() {
    return countNumber;
  }

  public void setSpotStatus(String spotStatus) {
    this.spotStatus = spotStatus;
  }

  public void setCountNumber(int countNumber) {
    this.countNumber = countNumber;
  }

  @Override
  public String toString() {
    return "Spot { "
        + "spotStatus = '"
        + spotStatus
        + '\''
        + " , countNumber = "
        + countNumber
        + '}';
  }
}
