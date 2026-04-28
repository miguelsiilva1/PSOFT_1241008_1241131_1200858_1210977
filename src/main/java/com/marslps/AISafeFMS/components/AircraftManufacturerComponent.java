package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.model.entities.AircraftManufacturer;
import com.marslps.AISafeFMS.repository.AircraftManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
public class AircraftManufacturerComponent implements CommandLineRunner {
    @Autowired
    private AircraftManufacturerRepository aircraft_manu_repo;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource("manufacturers.json").getInputStream();
        List<AircraftManufacturer> manufacturers = mapper.readValue(file, new TypeReference<>() {
        });
        aircraft_manu_repo.saveAll(manufacturers);
    }
}
