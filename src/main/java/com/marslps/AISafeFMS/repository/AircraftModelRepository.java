package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.AircraftModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
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
    @Query("SELECT am FROM AircraftModel am WHERE am.name LIKE :name AND am.seating_capacity = :seating_capacity")
    AircraftModel findByNameAndSeatingCapacity(@Param("name") String name, @Param("seating_capacity") int seating_capacity);

    @Query("""
        SELECT am FROM AircraftModel am WHERE am.name LIKE :name
            AND am.seating_capacity = :seating_capacity
            AND am.max_range = :max_range AND am.fuel_capacity = :fuel_capacity
            AND am.cruising_speed = :cruising_speed AND am.manufacturer.name = :manufacturer
    """)
    AircraftModel findAircraftModel(@Param("name") String name,
                                    @Param("seating_capacity") int seating_capacity,
                                    @Param("max_range") double max_range,
                                    @Param("fuel_capacity") double fuel_capacity,
                                    @Param("cruising_speed") double cruising_speed,
                                    @Param("manufacturer") String manufacturer_name);

    @Query("""
        UPDATE AircraftModel a
        SET a.seating_capacity = :new_seating_capacity,
            a.max_range = :new_max_range,
            a.fuel_capacity = :new_fuel_capacity,
            a.cruising_speed = :new_cruising_speed
        WHERE a = :old_aircraft_model
    """)
    @Modifying(clearAutomatically = true)
    @Transactional
    int update(@Param("old_aircraft_model") AircraftModel old_aircraft_model,
               @Param("new_seating_capacity") int new_seating_capacity,
               @Param("new_max_range") double new_max_range,
               @Param("new_fuel_capacity") double new_fuel_capacity,
               @Param("new_cruising_speed") double new_cruising_speed);
}
