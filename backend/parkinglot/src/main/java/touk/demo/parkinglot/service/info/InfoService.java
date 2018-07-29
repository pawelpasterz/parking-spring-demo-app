package touk.demo.parkinglot.service.info;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import touk.demo.parkinglot.converter.SpotToParkingSpotInfoConverter;
import touk.demo.parkinglot.converter.SpotToSpotInfoConverter;
import touk.demo.parkinglot.model.dto.ParkingSpotInfo;
import touk.demo.parkinglot.model.dto.SpotInfo;
import touk.demo.parkinglot.model.error.InvalidOptionRequestException;
import touk.demo.parkinglot.repository.SpotsRepository;

@AllArgsConstructor
@Service
public class InfoService implements ManagementService {

  @NonNull
  private final SpotsRepository spotsRepository;

  @NonNull
  private final SpotToParkingSpotInfoConverter parkingInfoConverter;
  
  @NonNull
  private final SpotToSpotInfoConverter spotConverter;

  @Override
  public ParkingSpotInfo getParkingInfo() {

    return parkingInfoConverter.apply(spotsRepository.findAll());
  }

  @Override
  public SpotInfo getOptionSpotInfo(String option) throws InvalidOptionRequestException {
    return spotsRepository
        .findById(option)
        .map(spotConverter)
        .orElseThrow(() -> new InvalidOptionRequestException(
            "Incorrect option value (should be either free or occupied) -- " + option));
  }
}
