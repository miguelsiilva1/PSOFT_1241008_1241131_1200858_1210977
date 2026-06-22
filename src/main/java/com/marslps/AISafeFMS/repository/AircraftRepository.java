package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.Aircraft;
import com.marslps.AISafeFMS.model.entities.AircraftModel;
import com.marslps.AISafeFMS.model.enums.AircraftStatus;
import com.marslps.AISafeFMS.model.vo.AircraftRegistration;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AircraftRepository extends CrudRepository<Aircraft, Long>  {
    @Query("SELECT a FROM Aircraft a WHERE a.registration_number.registration_number LIKE :reg_num")
    Aircraft findByRegistrationNumber(@Param("reg_num") String registration_number);
    @Query("SELECT a FROM Aircraft a WHERE a.model.name LIKE :name")
    List<Aircraft> findByModelName(@Param("name") String name);
    @Query("SELECT a FROM Aircraft a WHERE YEAR(a.manufactoring_date) = :year")
    List<Aircraft> findByManufactoringYear(@Param("year") int year);

    List<Aircraft> findByStatus(AircraftStatus status);

    @Query("SELECT a FROM Aircraft a")
    List<Aircraft> getAllAircraft();

    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END 
        FROM Aircraft a 
        WHERE a.registration_number.registration_number = :reg_num 
    """)
    boolean alreadyExists(@Param("reg_num") String registration_number);

    @Query("UPDATE Aircraft a SET a.status = :status WHERE a.registration_number.registration_number = :reg_num")
    @Modifying(clearAutomatically = true)
    @Transactional
    int updateAircraftStatus(@Param("reg_num") String registration_number, @Param("status") AircraftStatus status);

    @Query("""
        SELECT a FROM Aircraft a
        ORDER BY a.total_flight_hours DESC
    """)
    List<Aircraft> findTop5ByFlightHours(Pageable pageable);

    @Query("""
        SELECT a FROM Aircraft a
        LEFT JOIN Flight f ON f.aircraft = a
        GROUP BY a
        ORDER BY COUNT(f) DESC
    """)
    List<Aircraft> findTop5ByNumberOfAssignments(Pageable pageable);
}
