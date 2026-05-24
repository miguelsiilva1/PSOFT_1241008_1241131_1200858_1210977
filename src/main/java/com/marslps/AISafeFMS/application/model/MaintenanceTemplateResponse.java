package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import java.util.Set;

public record MaintenanceTemplateResponse(int template_id, String name, double cost, Set<String> applicable_models) {
    public static MaintenanceTemplateResponse from(MaintenanceTemplate template) {
        // Ajusta os 'getters' se os nomes no teu MaintenanceTemplate forem ligeiramente diferentes
        return new MaintenanceTemplateResponse(template.getTemplate_id(), template.getName(), template.getCost(), template.getApplicable_models());
    }
}