package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.repository.AircraftRepository;

import java.util.List;

@UseCase
public class FindAircraftByModelStatusManufactoringYearUseCase {
    private final AircraftRepository aircraft_repo;

    public FindAircraftByModelStatusManufactoringYearUseCase(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    public List<Aircraft> execute(String model_name) {
        List<Aircraft> aircraft_list = aircraft_repo.findByModelName(model_name);
        if (aircraft_list == null || aircraft_list.isEmpty()) {
            throw new IllegalArgumentException("There are no aircrafts of such model in the system");
        }
        return aircraft_list;
    }
    public List<Aircraft> execute(int year) {
        List<Aircraft> aircraft_list = aircraft_repo.findByManufactoringYear(year);
        if (aircraft_list == null || aircraft_list.isEmpty()) {
            throw new IllegalArgumentException("There are no aircrafts of such year in the system");
        }
        return aircraft_list;
    }
    public List<Aircraft> execute(AircraftStatus status) {
        List<Aircraft> aircraft_list = aircraft_repo.findByStatus(status);
        if (aircraft_list == null || aircraft_list.isEmpty()) {
            throw new IllegalArgumentException("There are no aircrafts with such status in the system");
        }
        return aircraft_list;
    }
}
