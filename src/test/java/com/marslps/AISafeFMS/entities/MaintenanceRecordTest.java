package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import com.marslps.AISafeFMS.model.entities.Aircraft;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Date;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MaintenanceRecordTest {

    @Test
    void testValidMaintenanceRecordCreation() {
        Date startDate = new Date();

        MaintenanceTemplate template = new MaintenanceTemplate("INSPECTION", "Routine Check", Set.of("A320"), 500.0);
        Aircraft aircraft = Mockito.mock(Aircraft.class);
        Mockito.when(aircraft.obtainRegistrationNumber()).thenReturn("CS-TJU");


        MaintenanceRecord record = new MaintenanceRecord(template, aircraft, "Engine routine check", startDate, 5.0, false);

        assertEquals("Engine routine check", record.getDescription());
        assertEquals(startDate, record.getStart_date());
        assertFalse(record.isCompleted());
    }

    @Test
    void testEquals() {
        Date sameDate = new Date();
        MaintenanceTemplate template = new MaintenanceTemplate("INSPECTION", "Routine Check", Set.of("A320"), 500.0);
        Aircraft aircraft = Mockito.mock(Aircraft.class);

        // Corrigido: Passado o 'false' no fim de ambos os registos
        MaintenanceRecord record1 = new MaintenanceRecord(template, aircraft, "Checkup", sameDate, 2.0, false);
        MaintenanceRecord record2 = new MaintenanceRecord(template, aircraft, "Checkup", sameDate, 2.0, false);

        assertEquals(record1, record2);
        assertEquals(record1.hashCode(), record2.hashCode());
    }
}