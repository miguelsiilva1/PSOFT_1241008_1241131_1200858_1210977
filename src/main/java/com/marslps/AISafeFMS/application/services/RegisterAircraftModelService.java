package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import org.springframework.stereotype.Service;

public interface RegisterAircraftModelService {
    AircraftModel register(String name, int seating_capacity, double max_range, double fuel_capacity, double cruising_speed, AircraftManufacturer manufacturer);
}
