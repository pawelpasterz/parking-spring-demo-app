package touk.demo.parkinglot.autosetup;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import touk.demo.parkinglot.model.entity.DriverType;
import touk.demo.parkinglot.model.entity.ParkingSpot;
import touk.demo.parkinglot.model.entity.Spot;
import touk.demo.parkinglot.repository.DriverTypeRepository;
import touk.demo.parkinglot.repository.ParkingSpotRepository;
import touk.demo.parkinglot.repository.SpotsRepository;

@Component
public class DemoSetUpClass implements CommandLineRunner {

  private final DriverTypeRepository driverRepository;
  private final ParkingSpotRepository parkingRepository;
  private final SpotsRepository spotsRepository;

  @Autowired
  public DemoSetUpClass(
      DriverTypeRepository driverRepository,
      ParkingSpotRepository parkingRepository,
      SpotsRepository spotsRepository) {
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
    Arrays.asList(
        new DriverType("VIP", 0, 2, 1.2),
        new DriverType("regular", 1, 2, 1.5)
    ).forEach(driverRepository::save);
  }

  private void setUpParkingDatabase() {
    for (int i = 1; i <= 10; i++) {
      parkingRepository.save(new ParkingSpot(false, null, null, null));
    }
  }

  private void setUpSpotsDatabase() {
    Arrays.asList(
        new Spot("occupied", 0),
        new Spot("free", 10)
    ).forEach(spotsRepository::save);
  }
}
