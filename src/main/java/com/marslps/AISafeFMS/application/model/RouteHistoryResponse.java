package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.entities.RouteHistory;

import java.time.LocalDateTime;

public record RouteHistoryResponse(
        Long id,
        String route_id,
        String description,
        LocalDateTime registered_at
) {
    public static RouteHistoryResponse from(RouteHistory history) {
        return new RouteHistoryResponse(
                history.getId(),
                history.getRoute().obtainRouteID().id(),
                history.getDescription(),
                history.getRegistered_at()
        );
    }
}