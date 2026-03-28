package com.marslps.AISafeFMS.model.entities;

import com.marslps.AISafeFMS.model.vo.NonEmptyString;
import com.marslps.AISafeFMS.model.vo.PositiveDouble;
import org.apache.axis.types.PositiveInteger;

import java.util.Date;

public class MaintenanceRecord extends MaintenanceTemplate {
    private Aircraft aircraft;
    private NonEmptyString description;
    private Date start_date;
    private PositiveDouble expected_duration;
    private Date end_date;
    private boolean completed;
}
