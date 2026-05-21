package com.marslps.AISafeFMS.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.model.vo.NumberedItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.*;

@Entity
public class MaintenanceTemplate {
    @Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int template_id;
    @Enumerated
    private MaintenanceTemplateType type;
    @Column @NotBlank
    private String name;
    @ElementCollection @NotEmpty
    private Set<String> applicable_models;
    @JoinColumn @OneToOne(cascade = CascadeType.ALL)
    private Checklist checklist;
    @OneToOne(cascade = CascadeType.ALL)
    private MaintenancePart part;
    @Column @Positive
    private double cost;

    protected MaintenanceTemplate() {}

    @JsonCreator
    public MaintenanceTemplate(@JsonProperty("type") MaintenanceTemplateType type,
                               @JsonProperty("name") @NotBlank String name,
                               @JsonProperty("applicable_models") @NotEmpty Set<String> applicable_models,
                               @JsonProperty("checklist") Checklist checklist,
                               @JsonProperty("part") MaintenancePart part,
                               @JsonProperty("cost") @Positive double cost) {
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MaintenanceTemplate template)) {return false;}
        return Objects.equals(this.template_id, template.template_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.template_id);
    }
}
