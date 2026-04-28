package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.model.entities.AirportType;
import com.marslps.AISafeFMS.repository.AirportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

@Component
public class AirportTypeComponent implements CommandLineRunner {
    @Autowired
    private AirportTypeRepository airport_type_repo;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream file = new ClassPathResource("airport_types.json").getInputStream();
        List<AirportType> types = mapper.readValue(file, new TypeReference<>() {});
        airport_type_repo.saveAll(types);
    }
}

