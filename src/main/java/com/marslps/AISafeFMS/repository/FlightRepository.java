package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Flight;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.vo.FlightID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface FlightRepository extends CrudRepository<Flight, Long> {

    @Query("SELECT COUNT(f) > 0 FROM Flight f WHERE f.id = :flightId")
    boolean existsByFlightId(@Param("flightId") FlightID flightId);

    @Query("SELECT COUNT(f) > 0 FROM Flight f WHERE f.aircraft = :aircraft AND f.scheduled_departure = :scheduledDeparture")
    boolean existsByAircraftAndScheduledDeparture(@Param("aircraft") Aircraft aircraft,
                                                  @Param("scheduledDeparture") Date scheduledDeparture);

    @Query("SELECT f FROM Flight f WHERE f.aircraft = :aircraft ORDER BY f.scheduled_departure ASC")
    List<Flight> findByAircraftOrderByScheduledDepartureAsc(@Param("aircraft") Aircraft aircraft);

    @Query("SELECT COUNT(f) FROM Flight f WHERE f.route = :route")
    long countByRoute(@Param("route") Route route);
}