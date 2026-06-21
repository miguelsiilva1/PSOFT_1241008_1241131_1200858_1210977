package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.model.vo.MaintenanceCompletionNotes;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
public class MaintenanceRecord {
    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int record_id;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private MaintenanceTemplate template;

    @JoinColumn(name = "aircraft_db_id")
    @ManyToOne
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

    @Embedded
    private MaintenanceCompletionNotes completion_notes;

    @Enumerated(EnumType.STRING)
    @Column(name = "component")
    private MaintenanceComponent component;

    protected MaintenanceRecord() {}

    public MaintenanceRecord(MaintenanceTemplate maintenance_template,
                             Aircraft aircraft,
                             @NotBlank String description,
                             Date start_date,
                             @Positive double expected_duration,
                             boolean completed) {
        if (maintenance_template == null) {
            throw new IllegalArgumentException("Maintenance template is required.");
        }
        if (aircraft == null) {
            throw new IllegalArgumentException("Aircraft is required.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is required.");
        }
        if (start_date == null) {
            throw new IllegalArgumentException("Start date is required.");
        }
        if (expected_duration <= 0) {
            throw new IllegalArgumentException("Expected duration must be positive.");
        }
        this.template = maintenance_template;
        this.aircraft = aircraft;
        this.description = description;
        this.start_date = start_date;
        this.expected_duration = expected_duration;
        this.end_date = new Date(start_date.getTime() + ((long) expected_duration * 60 * 60 * 1000));
        this.completed = completed;
        this.completion_notes = new MaintenanceCompletionNotes("");
    }

    public void complete(String completion_notes) {
        if (this.completed) {
            throw new IllegalArgumentException("Maintenance record is already completed.");
        }
        if (completion_notes == null || completion_notes.isBlank()) {
            throw new IllegalArgumentException("Completion notes are required.");
        }
        this.completed = true;
        this.completion_notes = new MaintenanceCompletionNotes(completion_notes);
        this.end_date = new Date();
    }

    public int obtainRecordId() {
        return this.record_id;
    }

    public void categorize(MaintenanceComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("The maintenance component category cannot be null.");
        }
        this.component = component;
    }
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MaintenanceRecord maintenance_record)) {return false;}
        return Objects.equals(this.record_id, maintenance_record.record_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.record_id);
    }
    public Aircraft getAircraft() {
        return this.aircraft;
    }
}