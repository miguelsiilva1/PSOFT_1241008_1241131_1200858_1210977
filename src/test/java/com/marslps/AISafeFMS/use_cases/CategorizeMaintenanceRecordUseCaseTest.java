package com.marslps.AISafeFMS.use_cases;

import com.marslps.AISafeFMS.application.use_cases.CategorizeMaintenanceRecordUseCase;
import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.repository.MaintenanceRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategorizeMaintenanceRecordUseCaseTest {

    @Mock
    private MaintenanceRecordRepository repository;

    @InjectMocks
    private CategorizeMaintenanceRecordUseCase useCase;

    @Test
    void shouldCategorizeRecordSuccessfully() {
        MaintenanceRecord record = mock(MaintenanceRecord.class);
        when(repository.findById(1)).thenReturn(Optional.of(record));
        when(repository.save(record)).thenReturn(record);

        MaintenanceRecord result = useCase.execute(1, "ENGINE");

        assertNotNull(result);
        verify(record).categorize(MaintenanceComponent.ENGINE);
        verify(repository).save(record);
    }

    @Test
    void shouldThrowExceptionWhenRecordNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(1, "ENGINE"));
    }

    @Test
    void shouldThrowExceptionWhenComponentIsInvalid() {
        MaintenanceRecord record = mock(MaintenanceRecord.class);
        when(repository.findById(1)).thenReturn(Optional.of(record));

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(1, "COMPONENTE_INVALIDO"));
    }
}