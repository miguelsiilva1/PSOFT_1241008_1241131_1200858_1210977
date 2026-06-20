package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.model.vo.RouteID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("select r from Route r where r.id = :routeId")
    Optional<Route> findByRouteId(@Param("routeId") RouteID routeId);

    @Query("select count(r) > 0 from Route r where r.id = :routeId")
    boolean existsByRouteId(@Param("routeId") RouteID routeId);

    List<Route> findByDeparture_Iata(LocationIdentifier iata);

    List<Route> findByDestination_Iata(LocationIdentifier iata);

    List<Route> findByDeparture_IataAndDestination_Iata(
            LocationIdentifier departureIata,
            LocationIdentifier destinationIata
    );

    @Query("""
        SELECT r FROM Route r
        WHERE :range >= r.min_aircraft_range
            AND :capacity >= r.min_aircraft_capacity
    """)
    List<Route> findValidRoutesForAircraft(@Param("range") double range, @Param("capacity") int capacity);
}