package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import java.util.List;

public interface ViewGroupedAirportsService {
    List<Airport> searchAirports(String region, String country);
}