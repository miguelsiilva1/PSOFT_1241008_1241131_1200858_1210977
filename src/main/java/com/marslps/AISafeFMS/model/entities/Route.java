package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import com.marslps.AISafeFMS.model.vo.RouteID;

public class Route {
    private RouteID id;
    private Airport departure;
    private Airport destination;
    private PositiveDouble flight_time;
    private PositiveDouble flight_distance;
    private PositiveDouble min_aircraft_range;
    private PositiveDouble min_aircraft_capacity;
    private boolean active;
}
