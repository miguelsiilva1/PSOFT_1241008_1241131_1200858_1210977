package com.marslps.AISafeFMS.use_cases;

import com.marslps.AISafeFMS.application.model.MaintenanceCostReportResponse;
import com.marslps.AISafeFMS.application.use_cases.GenerateMaintenanceCostReportUseCase;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenerateMaintenanceCostReportUseCaseTest {

    @Mock
    private MaintenanceRecordRepository repository;

    @InjectMocks
    private GenerateMaintenanceCostReportUseCase useCase;

    @Test
    void shouldGenerateReportGroupByAircraft() {
        List<Object[]> mockData = new ArrayList<>();
        mockData.add(new Object[]{"CS-TJU", 5000.0});
        when(repository.getCostReportPerAircraft()).thenReturn(mockData);

        List<MaintenanceCostReportResponse> result = useCase.execute("aircraft");

        assertEquals(1, result.size());
        assertEquals("CS-TJU", result.get(0).target());
        assertEquals(5000.0, result.get(0).totalCost());
    }

    @Test
    void shouldThrowExceptionOnInvalidGroupBy() {
        assertThrows(IllegalArgumentException.class, () -> useCase.execute("modelo_inventado"));
    }
}