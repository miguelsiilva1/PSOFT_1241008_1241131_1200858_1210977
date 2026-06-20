package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.ViewGroupedAirportsService;
import com.marslps.AISafeFMS.model.entities.Airport;
import org.springframework.stereotype.Component;

import java.util.List;

@UseCase
public class ViewGroupedAirportsUseCase {
    private final ViewGroupedAirportsService service;

    public ViewGroupedAirportsUseCase(ViewGroupedAirportsService service) {
        this.service = service;
    }

    public List<Airport> execute(String region, String country) {
        return service.searchAirports(region, country);
    }
}