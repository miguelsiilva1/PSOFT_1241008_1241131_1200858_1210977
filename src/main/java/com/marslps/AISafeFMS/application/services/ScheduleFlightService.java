package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Flight;
import com.marslps.AISafeFMS.model.entities.Route;

import java.util.Date;

public interface ScheduleFlightService {

    Flight schedule(Route route, Aircraft aircraft, Date scheduledDeparture);
}