package touk.demo.parkinglot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.converter.BaseConverter;
import touk.demo.parkinglot.model.dto.FreeSpotInfo;
import touk.demo.parkinglot.model.dto.OccupiedSpotInfo;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.entity.Spot;
import touk.demo.parkinglot.repository.SpotsRepository;

@Service
public class ParkingService implements ManagementService {

  private final SpotsRepository spotsRepository;
  private final BaseConverter<List<Spot>, ParkingSpotInfo> parkingInfoConverter;
  private final BaseConverter<Spot, FreeSpotInfo> freeSpotConverter;
  private final BaseConverter<Spot, OccupiedSpotInfo> occupiedConverter;

  @Autowired
  public ParkingService(
      SpotsRepository spotsRepository,
      BaseConverter<List<Spot>, ParkingSpotInfo> parkingInfoConverter,
      BaseConverter<Spot, FreeSpotInfo> freeSpotConverter,
      BaseConverter<Spot, OccupiedSpotInfo> occupiedConverter) {
    this.spotsRepository = spotsRepository;
    this.parkingInfoConverter = parkingInfoConverter;
    this.freeSpotConverter = freeSpotConverter;
    this.occupiedConverter = occupiedConverter;
  }

  @Override
  public ParkingSpotInfo getParkingInfo() {
    return parkingInfoConverter.convert(spotsRepository.findAll());
  }

  @Override
  public FreeSpotInfo getFreeSpotNumber() {
    return freeSpotConverter.convert(spotsRepository.findById("free").get());
  }

  @Override
  public OccupiedSpotInfo getOccupiedSpotNumber() {
    return occupiedConverter.convert(spotsRepository.findById("occupied").get());
  }

}
