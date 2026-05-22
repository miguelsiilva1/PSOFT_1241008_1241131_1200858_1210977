package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.SeatingConfiguration;

import java.util.Date;

public interface RegisterAircraftService {
    Aircraft register(AircraftRegistration aircraft_registration,
                      AircraftModel aircraft_model,
                      Date manufacturing_date,
                      Date last_maintenance,
                      int max_flight_hours_until_maintenance,
                      int max_days_until_maintenance,
                      SeatingConfiguration seating_configuration,
                      AircraftStatus aircraft_status);
}
