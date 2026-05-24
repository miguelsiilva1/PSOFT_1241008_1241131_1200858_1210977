package com.marslps.AISafeFMS.application.model;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public record CreateMaintenanceTemplateRequest(
        @NotBlank String name,
        @NotBlank String type,
        Set<String> applicable_models,
        double cost
) {}