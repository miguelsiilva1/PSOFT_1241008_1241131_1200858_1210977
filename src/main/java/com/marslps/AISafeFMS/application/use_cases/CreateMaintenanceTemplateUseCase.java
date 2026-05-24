package com.marslps.AISafeFMS.application.use_cases;

import com.marslps.AISafeFMS.application.model.CreateMaintenanceTemplateRequest;
import com.marslps.AISafeFMS.application.services.MaintenanceServiceImpl;
import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import org.springframework.stereotype.Component;


@Component
public class CreateMaintenanceTemplateUseCase {
    private final MaintenanceServiceImpl service;
    public CreateMaintenanceTemplateUseCase(MaintenanceServiceImpl service) { this.service = service; }

    public MaintenanceTemplate execute(CreateMaintenanceTemplateRequest request) {
        return service.createMaintenanceTemplate(request);
    }
}