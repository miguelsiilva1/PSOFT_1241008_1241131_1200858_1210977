package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewGroupedAirportsServiceImpl implements ViewGroupedAirportsService {

    private final AirportRepository airport_repository;

    public ViewGroupedAirportsServiceImpl(AirportRepository airport_repository) {
        this.airport_repository = airport_repository;
    }

    @Override
    public List<Airport> searchAirports(String region, String country) {
        if (region != null && !region.isBlank()) {
            return airport_repository.groupByRegion(region);
        } else if (country != null && !country.isBlank()) {
            return airport_repository.groupByCountry(country);
        }
        
        throw new IllegalArgumentException("Please provide a region or country parameter.");
    }
}