package touk.demo.parkinglot.repository;

import org.springframework.data.repository.CrudRepository;
import touk.demo.parkinglot.model.entity.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {

  int countByOccupied(boolean bool);
  int countById(int i);
}
