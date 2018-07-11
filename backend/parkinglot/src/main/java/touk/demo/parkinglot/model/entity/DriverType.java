package touk.demo.parkinglot.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driver_type")
public class DriverType {

  @Id
  private String roleName;

  private int firstHour;
  private int secondHour;
  private double thirdHour;

  public DriverType() {}

  public DriverType(String roleName, int firstHour, int secondHour, double thirdHour) {
    this.roleName = roleName;
    this.firstHour = firstHour;
    this.secondHour = secondHour;
    this.thirdHour = thirdHour;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public int getFirstHour() {
    return firstHour;
  }

  public void setFirstHour(int firstHour) {
    this.firstHour = firstHour;
  }

  public int getSecondHour() {
    return secondHour;
  }

  public void setSecondHour(int secondHour) {
    this.secondHour = secondHour;
  }

  public double getThirdHour() {
    return thirdHour;
  }

  public void setThirdHour(double thirdHour) {
    this.thirdHour = thirdHour;
  }

  @Override
  public String toString() {
    return "DriverType{"
        + "roleName='"
        + roleName
        + '\''
        + ", firstHour="
        + firstHour
        + ", secondHour="
        + secondHour
        + ", thirdHour="
        + thirdHour
        + '}';
  }
}
