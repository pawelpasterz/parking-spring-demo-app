package pl.pasterz.demo.parkinglot.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationConfirm {

  private int spotId;
  private String driverType;

  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MMM-dd HH:mm:ss ")
  @DateTimeFormat(iso = ISO.DATE_TIME)
  private Date beginTime;
}
