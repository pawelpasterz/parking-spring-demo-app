package touk.demo.parkinglot.service.info;

import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.dto.SpotInfo;

public interface ManagementService {

  ParkingSpotInfo getParkingInfo();
  SpotInfo getOptionSpotInfo(String option);
}
