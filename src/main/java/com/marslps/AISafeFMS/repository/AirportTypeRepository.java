package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.AirportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AirportTypeRepository extends CrudRepository<AirportType, Long> {

}
