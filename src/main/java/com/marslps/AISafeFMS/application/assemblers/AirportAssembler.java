package com.marslps.AISafeFMS.application.assemblers;

import com.marslps.AISafeFMS.application.dto.AirportDTO;
import com.marslps.AISafeFMS.model.entities.Airport;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AirportAssembler implements RepresentationModelAssembler<Airport, AirportDTO> {

    @Override
    public AirportDTO toModel(Airport airport) {
        return new AirportDTO(
                airport.obtainName(),
                airport.obtainIata(),
                airport.obtainCoordinates(),
                airport.obtainAirport_location(),
                airport.obtainAirport_type(),
                airport.obtainCertified_models(),
                airport.obtainRunway_info(),
                airport.obtainStatus(),
                airport.obtainTime_zone(),
                airport.obtainOperational_hours(),
                airport.obtainContact_info(),
                airport.obtainTerminals(),
                airport.obtainGates(),
                airport.obtainServices(),
                airport.obtainImages()
        );
    }
}