package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.UseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@UseCase
public class TopAircraftsByNumberOfAssignmentsUseCase {
    private final AircraftRepository aircraft_repo;

    public TopAircraftsByNumberOfAssignmentsUseCase(AircraftRepository aircraft_repo) {
        this.aircraft_repo = aircraft_repo;
    }

    public List<Aircraft> execute() {
        return aircraft_repo.findTop5ByNumberOfAssignments(PageRequest.of(0, 5));
    }
}
