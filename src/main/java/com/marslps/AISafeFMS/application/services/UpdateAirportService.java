package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.enums.AirportStatus;

public interface UpdateAirportService {
    Airport updateStatus(Airport airport, AirportStatus status);
}