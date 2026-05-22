package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;

public interface ViewAirportDetailsService {
    Airport getDetails(Airport airport);
}