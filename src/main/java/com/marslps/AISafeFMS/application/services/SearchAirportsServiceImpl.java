package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchAirportsServiceImpl implements SearchAirportsService {
    private final AirportRepository airport_repository;

    public SearchAirportsServiceImpl(AirportRepository airportRepository) {
        this.airport_repository = airportRepository;
    }

    @Override
    public List<Airport> searchAirports(String city, String country, String name) {
        if (name != null && !name.isBlank()) {
            return airport_repository.findByName(name);
        } else if (city != null && !city.isBlank()) {
            return airport_repository.findByCity(city);
        } else if (country != null && !country.isBlank()) {
            return airport_repository.findByCountry(country);
        } 
        return (List<Airport>) airport_repository.findAll();
    }
}