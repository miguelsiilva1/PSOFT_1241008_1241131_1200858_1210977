package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MaintenanceTemplate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "maintenance_template_db_id")
    private Long db_id;

    @Enumerated
    private MaintenanceTemplateType type;
    @Embedded
    private NonEmptyString name;
    @ElementCollection
    private Set<NonEmptyString> applicable_models;
    @OneToMany
    private List<NumberedItem> checklist;
    @Enumerated
    private MaintenanceComponent component;
    @Embedded
    private PositiveDouble cost;

    public MaintenanceTemplate() {
        this.type = MaintenanceTemplateType.OVERHAUL;
        this.name = new NonEmptyString();
        this.applicable_models = new HashSet<>();
        this.checklist = new ArrayList<>();
        this.component = MaintenanceComponent.EXTERIOR;
        this.cost = new PositiveDouble(30.9);
    }

    public MaintenanceTemplate(MaintenanceTemplateType type,
                               NonEmptyString name,
                               Set<NonEmptyString> applicable_models,
                               List<NumberedItem> checklist,
                               MaintenanceComponent component,
                               PositiveDouble cost) {
        this.type = type;
        this.name = name;
        this.applicable_models = applicable_models;
        this.checklist = checklist;
        this.component = component;
        this.cost = cost;
    }
}
