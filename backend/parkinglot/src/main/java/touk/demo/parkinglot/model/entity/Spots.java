package touk.demo.parkinglot.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spots")
public class Spots {

  @Id
  private String spotStatus;
  private int countNumber;

  public Spots() {}

  public String getSpotStatus() {
    return spotStatus;
  }

  public int getCountNumber() {
    return countNumber;
  }

  @Override
  public String toString() {
    return "Spots { "
        + "spotStatus = '"
        + spotStatus
        + '\''
        + " , countNumber = "
        + countNumber
        + '}';
  }
}
