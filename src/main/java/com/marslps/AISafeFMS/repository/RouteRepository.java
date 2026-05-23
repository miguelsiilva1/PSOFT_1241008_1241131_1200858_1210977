package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {

    Optional<Route> findById_Id(String id);

    boolean existsById_Id(String id);

    List<Route> findByDeparture_Iata(LocationIdentifier iata);

    List<Route> findByDestination_Iata(LocationIdentifier iata);

    List<Route> findByDeparture_IataAndDestination_Iata(
            LocationIdentifier departureIata,
            LocationIdentifier destinationIata
    );
}