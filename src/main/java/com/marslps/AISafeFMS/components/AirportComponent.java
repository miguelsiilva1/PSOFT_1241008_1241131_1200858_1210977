package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.application.model.RegisterAirportRequest;
import com.marslps.AISafeFMS.application.use_cases.RegisterAirportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
public class AirportComponent implements CommandLineRunner {
    @Autowired
    private RegisterAirportUseCase register_airport_use_case;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource("airports.json").getInputStream();
        List<RegisterAirportRequest> data = mapper.readValue(file, new TypeReference<>() {});

        for (RegisterAirportRequest req : data) {
            try {
                register_airport_use_case.execute(req.name(), req.iata(), req.coordinates(), req.airport_location(),
                        req.airport_type(), req.runway_info(), req.status(), req.time_zone(), req.operational_hours(),
                        req.contact_info(), req.terminals(), req.gates(), req.services(), req.images());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}