package pl.pasterz.demo.parkinglot.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import pl.pasterz.demo.parkinglot.model.entity.Spot;

public interface SpotsRepository extends CrudRepository<Spot, String> {

  List<Spot> findAll();
  Optional<Spot> findById(String id);
}
