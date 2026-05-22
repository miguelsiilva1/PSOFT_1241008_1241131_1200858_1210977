package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;

import java.util.List;

public interface SearchAirportsService {
    List<Airport> searchAirports(String city, String country, String name);
}