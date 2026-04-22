package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.model.vo.AirportType;
import com.marslps.AISafeFMS.repository.AirportTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AirportTypeComponent implements CommandLineRunner {
    @Autowired
    private AirportTypeRepository airport_type_repo;

    @Override
    public void run(String... args) throws Exception {
        airport_type_repo.save(new AirportType("Commercial Service"));
        airport_type_repo.save(new AirportType("Primary Large Hub"));
        airport_type_repo.save(new AirportType("Primary Medium Hub"));
        airport_type_repo.save(new AirportType("Primary Small Hub"));
        airport_type_repo.save(new AirportType("Primary NonHub"));
        airport_type_repo.save(new AirportType("Nonprimary NonHub"));
        airport_type_repo.save(new AirportType("Nonprimary Reliever"));
        airport_type_repo.save(new AirportType("Nonprimary General Aviation"));
    }
}

