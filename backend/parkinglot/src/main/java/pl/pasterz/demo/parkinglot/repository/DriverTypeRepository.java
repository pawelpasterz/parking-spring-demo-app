package pl.pasterz.demo.parkinglot.repository;

import org.springframework.data.repository.CrudRepository;
import pl.pasterz.demo.parkinglot.model.entity.DriverType;

public interface DriverTypeRepository extends CrudRepository<DriverType, String> {}
