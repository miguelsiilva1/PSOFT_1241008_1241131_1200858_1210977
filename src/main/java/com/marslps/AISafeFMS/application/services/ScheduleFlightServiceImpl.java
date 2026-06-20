package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.Flight;
import com.marslps.AISafeFMS.model.entities.Route;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.enums.AirportStatus;
import com.marslps.AISafeFMS.model.enums.FlightStatus;
import com.marslps.AISafeFMS.model.vo.FlightID;
import com.marslps.AISafeFMS.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ScheduleFlightServiceImpl implements ScheduleFlightService {

    private final FlightRepository flightRepository;

    public ScheduleFlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight schedule(Route route, Aircraft aircraft, Date scheduledDeparture) {
        if (route == null) {
            throw new IllegalArgumentException("Route not found.");
        }

        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft not found.");
        }

        if (scheduledDeparture == null) {
            throw new IllegalArgumentException("Scheduled departure is required.");
        }

        if (!route.isActive()) {
            throw new IllegalArgumentException("Route is not active.");
        }

        if (route.obtainDeparture().obtainStatus() != AirportStatus.OPERATIONAL ||
                route.obtainDestination().obtainStatus() != AirportStatus.OPERATIONAL) {
            throw new IllegalArgumentException("Both airports must be operational.");
        }

        if (!aircraft.obtainAircraftStatus().equals(AircraftStatus.AVAILABLE.toString())) {
            throw new IllegalArgumentException("Aircraft is not available.");
        }

        if (aircraft.obtainRange() < route.getMin_aircraft_range()) {
            throw new IllegalArgumentException("Aircraft range is not enough for this route.");
        }

        if (aircraft.obtainCapacity() < route.getMin_aircraft_capacity()) {
            throw new IllegalArgumentException("Aircraft capacity is not enough for this route.");
        }

        if (flightRepository.existsByAircraftAndScheduledDeparture(aircraft, scheduledDeparture)) {
            throw new IllegalArgumentException("Aircraft already has a flight scheduled for this date and time.");
        }

        FlightID flightID = generateFlightID(route, aircraft, scheduledDeparture);

        if (flightRepository.existsByFlightId(flightID)) {
            throw new IllegalArgumentException("Flight already exists.");
        }

        return new Flight(
                flightID,
                route,
                aircraft,
                scheduledDeparture,
                0,
                FlightStatus.SCHEDULED
        );
    }

    public String generateFlightIDValue(Route route, Aircraft aircraft, Date scheduledDeparture) {
        String date = new SimpleDateFormat("yyyyMMddHHmm").format(scheduledDeparture);

        return route.obtainRouteID().id()
                + "-"
                + aircraft.obtainRegistrationNumber()
                + "-"
                + date;
    }

    private FlightID generateFlightID(Route route, Aircraft aircraft, Date scheduledDeparture) {
        return new FlightID(generateFlightIDValue(route, aircraft, scheduledDeparture));
    }
}