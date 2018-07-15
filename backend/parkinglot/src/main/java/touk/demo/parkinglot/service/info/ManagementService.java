package touk.demo.parkinglot.service.info;

import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;

public interface ManagementService {

  ParkingSpotInfo getParkingInfo();
  FreeSpotInfo getFreeSpotNumber();
  OccupiedSpotInfo getOccupiedSpotNumber();
}
