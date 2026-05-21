package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AircraftModelRepository extends CrudRepository<AircraftModel, Long> {
    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END 
        FROM AircraftModel a 
        WHERE a.name = :name 
          AND a.seating_capacity = :seating_capacity 
          AND a.max_range = :max_range 
          AND a.fuel_capacity = :fuel_capacity 
          AND a.cruising_speed = :cruising_speed
    """)
    boolean alreadyExists(@Param("name") String name,
                          @Param("seating_capacity") int seating_capacity,
                          @Param("max_range") double max_range,
                          @Param("fuel_capacity") double fuel_capacity,
                          @Param("cruising_speed") double cruising_speed);

    List<AircraftModel> findByName(String name);
}
