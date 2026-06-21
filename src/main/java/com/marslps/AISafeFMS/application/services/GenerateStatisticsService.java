package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.dto.AirportStatisticsDTO;
import com.marslps.AISafeFMS.model.entities.Route;

import java.util.List;

public interface GenerateStatisticsService {
    List<AirportStatisticsDTO> generateStatistics(List<Route> all_routes);
}