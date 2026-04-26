package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
public class MaintenanceRecord extends MaintenanceTemplate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "maintenance_record_db_id")
    private Long db_id;
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
}
