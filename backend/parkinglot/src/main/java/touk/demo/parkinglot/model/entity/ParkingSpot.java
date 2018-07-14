package touk.demo.parkinglot.model.entity;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "parking_spots")
public final class ParkingSpot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private boolean occupied;

  private String carNumber;

  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate startDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "role_type")
  private DriverType driverType;

  public ParkingSpot() {}

  public ParkingSpot(
      boolean occupied, String carNumber, LocalDate startDate, DriverType driverType) {
    this.occupied = occupied;
    this.carNumber = carNumber;
    this.startDate = startDate;
    this.driverType = driverType;
  }

  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public DriverType getDriverType() {
    return driverType;
  }

  public void setDriverType(DriverType driverType) {
    this.driverType = driverType;
  }

  public boolean isOccupied() {
    return occupied;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ParkingSpot{"
        + "id="
        + id
        + ", occupied="
        + occupied
        + ", carNumber='"
        + carNumber
        + '\''
        + ", startDate="
        + startDate
        + ", driverType="
        + driverType
        + '}';
  }
}
