package touk.demo.parkinglot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.converter.BaseConverter;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.entity.Spots;
import touk.demo.parkinglot.repository.DriverTypeRepository;
import touk.demo.parkinglot.repository.ParkingSpotRepository;
import touk.demo.parkinglot.repository.SpotsRepository;

@Service
public class ParkingService implements ManagementService {

  private final SpotsRepository spotsRepository;
  private final DriverTypeRepository driverTypeRepository;
  private final ParkingSpotRepository parkingSpotRepository;
  private final BaseConverter<List<Spots>, ParkingSpotInfo> converter;

  @Autowired
  public ParkingService(
      SpotsRepository spotsRepository,
      DriverTypeRepository driverTypeRepository,
      ParkingSpotRepository parkingSpotRepository,
      BaseConverter<List<Spots>, ParkingSpotInfo> converter) {
    this.spotsRepository = spotsRepository;
    this.driverTypeRepository = driverTypeRepository;
    this.parkingSpotRepository = parkingSpotRepository;
    this.converter = converter;
  }

  @Override
  public ParkingSpotInfo getParkingInfo() {
    return converter.convert(spotsRepository.findAll());
  }
}
