package com.marslps.AISafeFMS.use_cases;

import com.marslps.AISafeFMS.application.model.MaintenanceTurnaroundResponse;
import com.marslps.AISafeFMS.application.use_cases.ViewAverageTurnaroundTimeUseCase;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
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
class ViewAverageTurnaroundTimeUseCaseTest {

    @Mock
    private MaintenanceRecordRepository repository;

    @InjectMocks
    private ViewAverageTurnaroundTimeUseCase useCase;

    @Test
    void shouldCalculateAverageTurnaroundTimeInHours() {
        List<Object[]> mockData = new ArrayList<>();
        Date start = new Date(126, 5, 23, 10, 0, 0);
        Date end = new Date(126, 5, 23, 15, 0, 0); // Exatamente 5 horas de intervalo
        mockData.add(new Object[]{"A320", start, end});

        when(repository.findCompletedTurnaroundData()).thenReturn(mockData);

        List<MaintenanceTurnaroundResponse> result = useCase.execute();

        assertEquals(1, result.size());
        assertEquals("A320", result.get(0).aircraftType());
        assertEquals(5.0, result.get(0).averageTurnaroundHours());
    }
}