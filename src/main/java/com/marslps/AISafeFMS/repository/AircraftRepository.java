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

import java.util.List;

public interface AircraftRepository extends CrudRepository<Aircraft, Long>  {
    @Query("SELECT a FROM Aircraft a WHERE a.registration_number.registration_number LIKE :reg_num")
    Aircraft findByRegistrationNumber(@Param("reg_num") String registration_number);
    @Query("SELECT a FROM Aircraft a WHERE a.model.name LIKE :name")
    List<Aircraft> findByModelName(@Param("name") String name);
    @Query("SELECT a FROM Aircraft a WHERE YEAR(a.manufactoring_date) = :year")
    List<Aircraft> findByManufactoringYear(@Param("year") int year);

    List<Aircraft> findByStatus(AircraftStatus status);
    @Query("""
        SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END 
        FROM Aircraft a 
        WHERE a.registration_number.registration_number = :reg_num 
    """)
    boolean alreadyExists(@Param("reg_num") String registration_number);

    @Query("UPDATE Aircraft a SET a.status = :status WHERE a.registration_number.registration_number = :reg_num")
    @Modifying
    @Transactional
    Aircraft updateAircraftStatus(@Param("reg_num") String registration_number, @Param("status") AircraftStatus status);
}
