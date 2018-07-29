package pl.pasterz.demo.parkinglot.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
@Entity
@Table(name = "spots")
public final class Spot {

  @Id
  @NonNull
  private String spotStatus;

  @NonNull
  private int countNumber;
}
