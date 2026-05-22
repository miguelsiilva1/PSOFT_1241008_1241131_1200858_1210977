package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.application.services.ViewAirportDetailsService;
import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.vo.LocationIdentifier;
import com.marslps.AISafeFMS.repository.AirportRepository;

@UseCase
public class ViewAirportDetailsUseCase {

    private final AirportRepository airport_repository;
    private final ViewAirportDetailsService view_airport_details_service;

    public ViewAirportDetailsUseCase(AirportRepository airport_repository, ViewAirportDetailsService view_airport_details_service) {
        this.airport_repository = airport_repository;
        this.view_airport_details_service = view_airport_details_service;
    }

    public Airport execute(LocationIdentifier iata) {
        Airport airport = airport_repository.findByIata(iata).orElse(null);
        if (airport == null) {
            throw new IllegalArgumentException("The request's data is malformed");
        }
        
        return view_airport_details_service.getDetails(airport);
    }
}