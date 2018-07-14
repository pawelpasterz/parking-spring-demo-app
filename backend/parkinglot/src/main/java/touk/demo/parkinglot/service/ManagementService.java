package touk.demo.parkinglot.service;

import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.interfaces.ParkingServiceResponse;

public interface ManagementService {

  ParkingSpotInfo getParkingInfo();
  FreeSpotInfo getFreeSpotNumber();
  OccupiedSpotInfo getOccupiedSpotNumber();
  ParkingServiceResponse postSpotReservation(String driver, String carNumber);
}
