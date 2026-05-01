package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MaintenanceTemplateRepository extends CrudRepository<MaintenanceTemplate, Long> {

}
