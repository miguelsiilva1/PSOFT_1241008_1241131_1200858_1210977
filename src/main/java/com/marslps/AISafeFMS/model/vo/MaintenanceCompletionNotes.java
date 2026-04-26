package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record MaintenanceCompletionNotes(String completion_notes) {
    public MaintenanceCompletionNotes(String completion_notes) {
        this.completion_notes = completion_notes;
    }
    public MaintenanceCompletionNotes() {
        this("");
    }
}
