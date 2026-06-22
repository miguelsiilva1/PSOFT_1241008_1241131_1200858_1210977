package com.marslps.AISafeFMS.use_cases;

import com.marslps.AISafeFMS.application.use_cases.ViewOngoingMaintenancesUseCase;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViewOngoingMaintenancesUseCaseTest {

    @Mock
    private MaintenanceRecordRepository repository;

    @InjectMocks
    private ViewOngoingMaintenancesUseCase useCase;

    @Test
    void shouldReturnOngoingMaintenancesSuccessfully() {
        MaintenanceRecord ongoingRecord = mock(MaintenanceRecord.class);
        when(repository.findByCompletedFalse()).thenReturn(Collections.singletonList(ongoingRecord));

        List<MaintenanceRecord> result = useCase.execute();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findByCompletedFalse();
    }
}