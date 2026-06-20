package com.marslps.AISafeFMS.application.services;

import com.marslps.AISafeFMS.application.dto.AirportStatisticsDTO;
import com.marslps.AISafeFMS.model.entities.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateStatisticsServiceImpl implements GenerateStatisticsService {

    @Override
    public List<AirportStatisticsDTO> generateStatistics(List<Route> all_routes) {
        List<AirportStatisticsDTO> statistics_list = new ArrayList<>();
        
        for (int r = 0; r < all_routes.size(); r++) {
            Route route = all_routes.get(r);
            String origin = route.obtainDeparture().obtainIata().iata();
            String destination = route.obtainDestination().obtainIata().iata();

            if (origin != null) {
                boolean found = false;
                for (int i = 0; i < statistics_list.size(); i++) {
                    if (statistics_list.get(i).iata.equals(origin)) {
                        statistics_list.get(i).total_routes++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    statistics_list.add(new AirportStatisticsDTO(origin, 1));
                }
            }
            
            if (destination != null) {
                boolean found = false;
                for (int i = 0; i < statistics_list.size(); i++) {
                    if (statistics_list.get(i).iata.equals(destination)) {
                        statistics_list.get(i).total_routes++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    statistics_list.add(new AirportStatisticsDTO(destination, 1));
                }
            }
        }
        
        for (int i = 0; i < statistics_list.size() - 1; i++) {
            for (int j = 0; j < statistics_list.size() - i - 1; j++) {
                if (statistics_list.get(j).total_routes < statistics_list.get(j + 1).total_routes) {
                    AirportStatisticsDTO temp = statistics_list.get(j);
                    statistics_list.set(j, statistics_list.get(j + 1));
                    statistics_list.set(j + 1, temp);
                }
            }
        }

        return statistics_list;
    }
}