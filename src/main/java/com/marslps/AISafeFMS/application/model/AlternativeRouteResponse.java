package com.marslps.AISafeFMS.application.model;

public record AlternativeRouteResponse(
        RouteResponse first_leg,
        RouteResponse second_leg,
        double total_distance
) {
}