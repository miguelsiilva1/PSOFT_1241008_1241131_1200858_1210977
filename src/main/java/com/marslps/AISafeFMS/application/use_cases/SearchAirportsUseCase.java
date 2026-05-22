package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.SearchAirportsService;
import com.marslps.AISafeFMS.model.entities.Airport;

import java.util.List;

@UseCase
public class SearchAirportsUseCase {
    private final SearchAirportsService search_airports_service;

    public SearchAirportsUseCase(SearchAirportsService search_airports_service) {
        this.search_airports_service = search_airports_service;
    }

    public List<Airport> execute(String city, String country, String name) {
        return search_airports_service.searchAirports(city, country, name);
    }
}