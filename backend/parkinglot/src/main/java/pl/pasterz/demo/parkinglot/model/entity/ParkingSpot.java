package pl.pasterz.demo.parkinglot.model.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "parking_spots")
public final class ParkingSpot {

  @Id
  @NonNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NonNull
  private boolean occupied;

  private String carNumber;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private Date startDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "role_type")
  private DriverType driverType;

  public ParkingSpot(
      boolean occupied,
      String carNumber,
      Date startDate,
      DriverType driverType
  ) {
    this.occupied = occupied;
    this.carNumber = carNumber;
    this.startDate = startDate;
    this.driverType = driverType;
  }
}
