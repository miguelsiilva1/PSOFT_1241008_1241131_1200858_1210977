package com.marslps.AISafeFMS.entities;

import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class MaintenanceTemplateTest {

    @Test
    void testValidMaintenanceTemplate() {
        // Usando o teu construtor público de 4 parâmetros
        MaintenanceTemplate template = new MaintenanceTemplate("INSPECTION", "Standard Inspection", Set.of("A320", "B737"), 150.0);

        assertEquals("Standard Inspection", template.getName());
        assertEquals(150.0, template.getCost());
        assertEquals(2, template.getApplicable_models().size());
    }

    @Test
    void testEquals() {

        MaintenanceTemplate template1 = new MaintenanceTemplate("INSPECTION", "Template A", Set.of("A320"), 100.0);
        MaintenanceTemplate template2 = new MaintenanceTemplate("OVERHAUL", "Template B", Set.of("B737"), 200.0);

        assertEquals(template1, template2);
        assertEquals(template1.hashCode(), template2.hashCode());
    }
}