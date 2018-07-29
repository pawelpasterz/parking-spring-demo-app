package pl.pasterz.demo.parkinglot.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "driver_type")
public final class DriverType {

  @Id
  private String roleName;

  private int firstHour;
  private int secondHour;
  private double thirdHour;
}
