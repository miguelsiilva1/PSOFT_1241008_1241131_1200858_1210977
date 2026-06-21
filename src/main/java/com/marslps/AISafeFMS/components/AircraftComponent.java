package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.application.use_cases.RegisterAircraftModelUseCase;
import com.marslps.AISafeFMS.application.use_cases.RegisterAircraftUseCase;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.repository.AircraftModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.core.annotation.Order;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
@Order(2)
public class AircraftComponent implements CommandLineRunner {
    @Autowired
    private RegisterAircraftUseCase register_aircraft;
    @Autowired
    private RegisterAircraftModelUseCase register_model;
    @Autowired
    private AircraftModelRepository am_repo;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource("aircrafts.json").getInputStream();

        List<Aircraft> aircrafts = mapper.readValue(file, new TypeReference<>() {});

        for (Aircraft aircraft : aircrafts) {
            try {
                AircraftModel model = aircraft.obtainModel();
                if(!am_repo.alreadyExists(model.obtainName(), model.obtainCapacity(), model.obtainRange(), model.obtainFuel(), model.obtainSpeed())) {
                    register_model.execute(model.obtainName(), model.obtainCapacity(), model.obtainRange(), model.obtainFuel(), model.obtainSpeed(), model.obtainManufacturer().obtainName());
                }
                register_aircraft.execute(aircraft.obtainRegistrationNumber(),
                        aircraft.obtainModelName(),
                        aircraft.obtainManufacturingDate(),
                        aircraft.obtainOperationalHours(),
                        aircraft.obtainFlightHours(),
                        aircraft.obtainLastMaintenance(),
                        aircraft.obtainMaxFlightHoursUntilMaintenance(),
                        aircraft.obtainMaxDaysUntilMaintenance(),
                        aircraft.obtainEconomySeats(),
                        aircraft.obtainPremiumEconomySeats(),
                        aircraft.obtainBusinessSeats(),
                        aircraft.obtainFirstSeats(),
                        aircraft.obtainAircraftStatus(),
                        aircraft.obtainImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}