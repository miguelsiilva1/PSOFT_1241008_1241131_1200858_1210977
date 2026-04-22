package com.marslps.AISafeFMS.components;

import com.marslps.AISafeFMS.model.vo.AircraftManufacturer;
import com.marslps.AISafeFMS.repository.AircraftManufacturerRepository;
import com.marslps.AISafeFMS.security.model.entities.SystemUser;
import com.marslps.AISafeFMS.security.model.enums.SystemUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AircraftManufacturerComponent implements CommandLineRunner {
    @Autowired
    private AircraftManufacturerRepository aircraft_manu_repo;

    @Override
    public void run(String... args) throws Exception {
        aircraft_manu_repo.save(new AircraftManufacturer("Boeing"));
        aircraft_manu_repo.save(new AircraftManufacturer("Airbus"));
        aircraft_manu_repo.save(new AircraftManufacturer("Embraer"));
        aircraft_manu_repo.save(new AircraftManufacturer("Bombardier"));
        aircraft_manu_repo.save(new AircraftManufacturer("Cessna"));
        aircraft_manu_repo.save(new AircraftManufacturer("Dessault Aviation"));
        aircraft_manu_repo.save(new AircraftManufacturer("Gulfstream Aerospace"));
        aircraft_manu_repo.save(new AircraftManufacturer("Sukhoi"));
        aircraft_manu_repo.save(new AircraftManufacturer("COMAC"));
        aircraft_manu_repo.save(new AircraftManufacturer("ATR"));
    }
}
