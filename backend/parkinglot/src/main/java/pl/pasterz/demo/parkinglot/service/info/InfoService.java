package pl.pasterz.demo.parkinglot.service.info;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import pl.pasterz.demo.parkinglot.repository.SpotsRepository;
import pl.pasterz.demo.parkinglot.converter.SpotToParkingSpotInfoConverter;
import pl.pasterz.demo.parkinglot.converter.SpotToSpotInfoConverter;
import pl.pasterz.demo.parkinglot.model.dto.ParkingSpotInfo;
import pl.pasterz.demo.parkinglot.model.dto.SpotInfo;
import pl.pasterz.demo.parkinglot.model.error.InvalidOptionRequestException;

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
