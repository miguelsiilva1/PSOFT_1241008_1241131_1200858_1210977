package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.FlightStatus;
import com.marslps.AISafeFMS.model.vo.FlightID;

public class Flight {
    private FlightID id;
    private Route route;
    private Aircraft aircraft;
    private FlightStatus status;
}
