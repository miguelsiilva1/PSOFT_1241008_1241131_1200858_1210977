package com.marslps.AISafeFMS.application.assemblers;

import com.marslps.AISafeFMS.application.dto.AircraftDTO;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AircraftAssembler implements RepresentationModelAssembler<Aircraft, AircraftDTO> {

    @Override
    public AircraftDTO toModel(Aircraft aircraft) {
        return new AircraftDTO(
                aircraft.obtainRegistrationNumber(),
                aircraft.obtainManufacturerName(),
                aircraft.obtainModelName(),
                aircraft.obtainEconomySeats(),
                aircraft.obtainPremiumEconomySeats(),
                aircraft.obtainBusinessSeats(),
                aircraft.obtainFirstSeats(),
                aircraft.obtainManufacturingDate(),
                aircraft.obtainLastMaintenance(),
                aircraft.obtainDaysSinceLastMaintenance(),
                aircraft.obtainMaxFlightHoursUntilMaintenance(),
                aircraft.obtainMaxDaysUntilMaintenance(),
                aircraft.obtainAircraftStatus(),
                aircraft.obtainImage()
        );
    }
}
