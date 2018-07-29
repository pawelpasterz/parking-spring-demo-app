package pl.pasterz.demo.parkinglot.service.info;

import pl.pasterz.demo.parkinglot.model.dto.ParkingSpotInfo;
import pl.pasterz.demo.parkinglot.model.dto.SpotInfo;

public interface ManagementService {

  ParkingSpotInfo getParkingInfo();
  SpotInfo getOptionSpotInfo(String option);
}
