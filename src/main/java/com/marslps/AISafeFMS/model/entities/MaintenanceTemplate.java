package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import com.marslps.AISafeFMS.model.enums.MaintenanceTemplateType;
import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;

import java.util.List;
import java.util.Set;

public class MaintenanceTemplate {
    private MaintenanceTemplateType type;
    private NonEmptyString name;
    private Set<NonEmptyString> applicable_models;
    private List<NumberedItem> checklist;
    private MaintenanceComponent component;
    private PositiveDouble cost;
}
