package com.marslps.AISafeFMS.use_cases;

import com.marslps.AISafeFMS.application.model.AircraftMaintenanceAlertResponse;
import com.marslps.AISafeFMS.application.use_cases.GetMaintenanceAlertsUseCase;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.repository.AircraftRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetMaintenanceAlertsUseCaseTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private GetMaintenanceAlertsUseCase useCase;

    @Test
    void shouldTriggerAlertWhenFlightHoursExceededLimits() {
        Aircraft aircraft = mock(Aircraft.class);
        when(aircraft.obtainRegistrationNumber()).thenReturn("CS-TJU");
        when(aircraft.obtainFlightHours()).thenReturn(1200.0);
        when(aircraft.obtainMaxFlightHoursUntilMaintenance()).thenReturn(1000); // Excedeu!
        when(aircraft.obtainLastMaintenance()).thenReturn(new Date());
        when(aircraft.obtainMaxDaysUntilMaintenance()).thenReturn(180);

        List<Aircraft> mockList = new ArrayList<>();
        mockList.add(aircraft);
        when(aircraftRepository.getAllAircraft()).thenReturn(mockList);

        List<AircraftMaintenanceAlertResponse> result = useCase.execute();

        assertEquals(1, result.size());
        assertEquals("CS-TJU", result.get(0).aircraftRegistration());
        assertTrue(result.get(0).reason().contains("Flight hours limit exceeded"));
    }
}