package com.marslps.AISafeFMS.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Date;
import java.util.Objects;

@Entity
public class MaintenanceRecord extends MaintenanceTemplate {
    @JoinColumn @ManyToOne
    private Aircraft aircraft;
    @Column @NotBlank
    private String description;
    @Temporal(TemporalType.DATE)
    private Date start_date;
    @Column @Positive
    private double expected_duration;
    @Temporal(TemporalType.DATE)
    private Date end_date;
    @Column
    private boolean completed;

    protected MaintenanceRecord() {}

    public MaintenanceRecord(MaintenanceTemplate maintenance_template,
                             Aircraft aircraft,
                             @NotBlank String description,
                             Date start_date,
                             @Positive double expected_duration,
                             boolean completed) {
        super(maintenance_template);
        this.aircraft = aircraft;
        this.description = description;
        this.start_date = start_date;
        this.expected_duration = expected_duration;
        this.end_date = new Date(start_date.getTime() + ((long) expected_duration * 60 * 60 * 1000));
        this.completed = completed;
    }
}
