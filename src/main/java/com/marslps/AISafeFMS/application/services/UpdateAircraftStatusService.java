package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;

public interface UpdateAircraftStatusService {
    Aircraft update(String registration_number, AircraftStatus status);
}
