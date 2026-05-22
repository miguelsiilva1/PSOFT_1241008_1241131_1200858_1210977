package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.Airport;
import org.springframework.stereotype.Service;

@Service
public class CertifyAircraftServiceImpl implements CertifyAircraftService {

    @Override
    public void certify(Airport airport, AircraftModel model) {
        airport.addCertifiedAircraftModel(model);
    }
}