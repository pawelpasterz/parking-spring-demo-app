package touk.demo.parkinglot.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import touk.demo.parkinglot.model.entity.Spots;

public interface SpotsRepository extends CrudRepository<Spots, String> {

  List<Spots> findAll();
}
