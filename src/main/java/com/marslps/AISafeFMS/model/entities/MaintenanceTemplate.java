package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import jakarta.persistence.*;

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
}
