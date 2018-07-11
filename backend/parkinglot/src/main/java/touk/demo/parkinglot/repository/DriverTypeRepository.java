package touk.demo.parkinglot.repository;

import org.springframework.data.repository.CrudRepository;
import touk.demo.parkinglot.model.entity.DriverType;

public interface DriverTypeRepository extends CrudRepository<DriverType, String> {}
