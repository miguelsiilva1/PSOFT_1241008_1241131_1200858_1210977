package com.marslps.AISafeFMS.use_cases;

import com.marslps.AISafeFMS.application.use_cases.SearchMaintenanceRecordsUseCase;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchMaintenanceRecordsUseCaseTest {

    @Mock
    private MaintenanceRecordRepository repository;

    @InjectMocks
    private SearchMaintenanceRecordsUseCase useCase;

    @Test
    void shouldSearchWithValidParameters() {
        Date start = new Date();
        Date end = new Date(start.getTime() + 3600000); // +1 hora
        when(repository.searchRecords("CS-TJU", start, end, MaintenanceComponent.ENGINE))
                .thenReturn(Collections.singletonList(mock(MaintenanceRecord.class)));

        List<MaintenanceRecord> result = useCase.execute("CS-TJU", start, end, "ENGINE");

        assertFalse(result.isEmpty());
        verify(repository).searchRecords("CS-TJU", start, end, MaintenanceComponent.ENGINE);
    }

    @Test
    void shouldThrowExceptionWhenStartDateAfterEndDate() {
        Date start = new Date();
        Date end = new Date(start.getTime() - 3600000); // -1 hora (Inválido!)

        assertThrows(IllegalArgumentException.class, () -> useCase.execute("CS-TJU", start, end, "ENGINE"));
    }
}