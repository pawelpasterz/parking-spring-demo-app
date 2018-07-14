package touk.demo.parkinglot.repository;

import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;
import touk.demo.parkinglot.model.entity.ParkingSpot;

public interface ParkingSpotRepository extends CrudRepository<ParkingSpot, Integer> {

  Stream<ParkingSpot> findAllByOccupiedOrderByIdAsc(boolean isOccupied);
}
