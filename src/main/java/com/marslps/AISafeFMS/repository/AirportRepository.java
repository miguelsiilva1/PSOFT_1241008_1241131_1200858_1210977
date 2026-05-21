package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    Optional<Airport> findByIata(LocationIdentifier iata);
}