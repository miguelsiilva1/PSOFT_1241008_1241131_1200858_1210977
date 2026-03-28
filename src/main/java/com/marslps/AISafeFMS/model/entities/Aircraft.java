package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import org.apache.axis.types.PositiveInteger;

import java.awt.image.BufferedImage;
import java.util.Date;

public class Aircraft {
    private AircraftRegistration registration_number;
    private NonEmptyString manufactor;
    private NonEmptyString model;
    private PositiveInteger number_of_seats;
    private PositiveDouble fuel_capacity;
    private PositiveDouble max_range;
    private PositiveDouble cruising_speed;
    private Date manufactoring_date;
    private BufferedImage image;
    private PositiveInteger last_maintenance;
    private PositiveInteger days_since_last_maintenance;
    private PositiveInteger max_flight_hours_until_maintenance;
    private PositiveInteger max_days_until_maintenance;
    private AircraftStatus status;

}
