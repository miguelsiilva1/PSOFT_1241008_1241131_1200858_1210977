package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRecordRepository extends CrudRepository<MaintenanceRecord, Integer> {


    @Query("SELECT m FROM MaintenanceRecord m WHERE m.aircraft.registration_number.registration_number = :regNum")
    List<MaintenanceRecord> findByAircraftRegistrationNumber(@Param("regNum") String regNum);


    @Query("SELECT SUM(m.expected_duration) FROM MaintenanceRecord m WHERE m.aircraft.registration_number.registration_number = :regNum")
    Double totalMaintenanceHoursByAircraftRegistrationNumber(@Param("regNum") String regNum);
}