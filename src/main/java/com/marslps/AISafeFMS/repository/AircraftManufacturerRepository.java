package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AircraftManufacturerRepository extends CrudRepository<AircraftManufacturer, Long> {

}
