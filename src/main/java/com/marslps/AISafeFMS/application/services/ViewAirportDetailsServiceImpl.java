package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Airport;
import org.springframework.stereotype.Service;

@Service
public class ViewAirportDetailsServiceImpl implements ViewAirportDetailsService {
    @Override
    public Airport getDetails(Airport airport) {
        return airport;
    }
}