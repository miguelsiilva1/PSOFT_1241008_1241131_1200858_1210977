package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.model.vo.Checklist;
import com.marslps.AISafeFMS.model.vo.NumberedItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.*;

@Entity
public class MaintenanceTemplate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "maintenance_template_db_id")
    private Long db_id;

    @Enumerated
    private MaintenanceTemplateType type;
    @Column @NotBlank
    private String name;
    @ElementCollection @NotBlank
    private Set<String> applicable_models;
    @Embedded
    private Checklist checklist;
    @OneToOne
    private MaintenancePart part;
    @Column @Positive
    private double cost;

    public MaintenanceTemplate() {
        this.type = MaintenanceTemplateType.OVERHAUL;
        this.name = "something";
        this.applicable_models = new HashSet<>(Arrays.asList("something"));
        this.checklist = new Checklist(new ArrayList<>(Arrays.asList(new NumberedItem())));
        this.part = new MaintenancePart();
        this.cost = 30.9;
    }
    public MaintenanceTemplate(MaintenanceTemplateType type,
                               @NotBlank String name,
                               Set<String> applicable_models,
                               Checklist checklist,
                               MaintenancePart part,
                               @Positive double cost) {
        this.type = type;
        this.name = name;
        this.applicable_models = applicable_models;
        this.checklist = checklist;
        this.part = part;
        this.cost = cost;
    }
    public MaintenanceTemplate(MaintenanceTemplate maintenance_template) {
        this.type = maintenance_template.type;
        this.name = maintenance_template.name;
        this.applicable_models = maintenance_template.applicable_models;
        this.checklist = maintenance_template.checklist;
        this.part = maintenance_template.part;
        this.cost = maintenance_template.cost;
    }
}
