package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.MaintenanceRecord;
import com.marslps.AISafeFMS.model.enums.MaintenanceComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintenanceRecordRepository extends JpaRepository<MaintenanceRecord, Integer> {


    @Query("SELECT r FROM MaintenanceRecord r WHERE r.aircraft.registration_number.registration_number = :reg")
    List<MaintenanceRecord> findByAircraftRegistrationNumber(@Param("reg") String registrationNumber);


    @Query("SELECT SUM(r.expected_duration) FROM MaintenanceRecord r WHERE r.aircraft.registration_number.registration_number = :reg")
    Double totalMaintenanceHoursByAircraftRegistrationNumber(@Param("reg") String registrationNumber);


    @Query("SELECT r FROM MaintenanceRecord r WHERE " +
            "(:aircraft IS NULL OR r.aircraft.registration_number.registration_number = :aircraft) AND " +
            "(:startDate IS NULL OR r.start_date >= :startDate) AND " +
            "(:endDate IS NULL OR r.start_date <= :endDate) AND " +
            "(:component IS NULL OR r.component = :component)")
    List<MaintenanceRecord> searchRecords(
            @Param("aircraft") String aircraft,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("component") MaintenanceComponent component
    );


    @Query("SELECT r.aircraft.registration_number.registration_number, SUM(r.template.cost) " +
            "FROM MaintenanceRecord r " +
            "GROUP BY r.aircraft.registration_number.registration_number")
    List<Object[]> getCostReportPerAircraft();


    @Query("SELECT r.aircraft.model.name, SUM(r.template.cost) " +
            "FROM MaintenanceRecord r " +
            "GROUP BY r.aircraft.model.name")
    List<Object[]> getCostReportPerModel();



    @Query("SELECT r.aircraft.model.name, r.start_date, r.end_date FROM MaintenanceRecord r WHERE r.completed = true")
    List<Object[]> findCompletedTurnaroundData();


    List<MaintenanceRecord> findByCompletedFalse();

}



