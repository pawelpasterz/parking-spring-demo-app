package pl.pasterz.demo.parkinglot.autosetup;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pasterz.demo.parkinglot.model.entity.Spot;
import pl.pasterz.demo.parkinglot.repository.DriverTypeRepository;
import pl.pasterz.demo.parkinglot.repository.ParkingSpotRepository;
import pl.pasterz.demo.parkinglot.repository.SpotsRepository;
import pl.pasterz.demo.parkinglot.model.entity.DriverType;
import pl.pasterz.demo.parkinglot.model.entity.ParkingSpot;

@Component
public class DemoSetUpClass implements CommandLineRunner {

  private int parkingLimit;
  private final DriverTypeRepository driverRepository;
  private final ParkingSpotRepository parkingRepository;
  private final SpotsRepository spotsRepository;

  @Autowired
  public DemoSetUpClass(
      @Value("${parking.spot.limit}") int parkingLimit,
      DriverTypeRepository driverRepository,
      ParkingSpotRepository parkingRepository,
      SpotsRepository spotsRepository) {
    this.parkingLimit = parkingLimit;
    this.driverRepository = driverRepository;
    this.parkingRepository = parkingRepository;
    this.spotsRepository = spotsRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    setUpDriverDatabase();
    setUpSpotsDatabase();
    setUpParkingDatabase();
  }

  private void setUpDriverDatabase() {
    Arrays.asList(new DriverType("VIP", 0, 2, 1.2), new DriverType("regular", 1, 2, 1.5))
        .forEach(driverRepository::save);
  }

  private void setUpParkingDatabase() {
    for (int i = 1; i <= parkingLimit; i++) {
      parkingRepository.save(new ParkingSpot(i, false));
    }
  }

  private void setUpSpotsDatabase() {
    Arrays.asList(new Spot("occupied", 0), new Spot("free", parkingLimit))
        .forEach(spotsRepository::save);
  }
}
