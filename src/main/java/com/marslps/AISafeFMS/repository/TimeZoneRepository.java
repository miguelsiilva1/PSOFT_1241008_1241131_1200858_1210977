package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.TimeZone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeZoneRepository extends CrudRepository<TimeZone, Long> {
    TimeZone findByAbbreviation(String abbreviation);
}