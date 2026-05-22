package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.entities.Airport;

public interface CertifyAircraftService {
    void certify(Airport airport, AircraftModel model);
}