package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateAirportServiceImpl implements UpdateAirportService {
    @Override
    public Airport updateStatus(Airport airport, AirportStatus status) {
        airport.updateStatus(status);
        return airport;
    }
}