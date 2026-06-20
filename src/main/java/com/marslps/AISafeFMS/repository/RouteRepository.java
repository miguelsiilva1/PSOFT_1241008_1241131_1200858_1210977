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

    @Query("SELECT r FROM Route r WHERE r.id = :routeId")
    Optional<Route> findByRouteId(@Param("routeId") RouteID routeId);

    @Query("SELECT COUNT(r) > 0 FROM Route r WHERE r.id = :routeId")
    boolean existsByRouteId(@Param("routeId") RouteID routeId);

    List<Route> findByDeparture_Iata(LocationIdentifier departure);

    List<Route> findByDestination_Iata(LocationIdentifier destination);

    List<Route> findByDeparture_IataAndDestination_Iata(LocationIdentifier departure,
                                                        LocationIdentifier destination);

    @Query("""
            SELECT r
            FROM Route r
            WHERE r.min_aircraft_range <= :aircraftRange
              AND r.min_aircraft_capacity <= :aircraftCapacity
              AND r.active = true
            """)
    List<Route> findValidRoutesForAircraft(@Param("aircraftRange") double aircraftRange,
                                           @Param("aircraftCapacity") int aircraftCapacity);

    @Query("SELECT r FROM Route r WHERE r.active = true")
    List<Route> findActiveRoutes();

    @Query("SELECT r FROM Route r WHERE r.active = true ORDER BY r.flight_distance ASC")
    List<Route> findActiveRoutesOrderByDistance();
}