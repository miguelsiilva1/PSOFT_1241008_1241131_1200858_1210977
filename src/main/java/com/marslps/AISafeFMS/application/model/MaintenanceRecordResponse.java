package com.marslps.AISafeFMS.application.model;

import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import java.util.Date;

public record MaintenanceRecordResponse(
        int record_id,
        String aircraft_registration,
        String description,
        Date start_date,
        Date end_date,
        boolean completed,
        String component
) {
    public static MaintenanceRecordResponse from(MaintenanceRecord record) {
        return new MaintenanceRecordResponse(
                record.obtainRecordId(),
                record.getAircraft().obtainRegistrationNumber(),
                record.getDescription(),
                record.getStart_date(),
                record.getEnd_date(),
                record.isCompleted(),
                record.getComponent() != null ? record.getComponent().name() : null
        );
    }
}