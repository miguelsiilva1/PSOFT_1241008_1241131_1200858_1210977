package com.marslps.AISafeFMS.model.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MaintenanceCompletionNotes {
    @Column
    private String completion_notes;

    public MaintenanceCompletionNotes() {
        this.completion_notes = "";
    }
}
