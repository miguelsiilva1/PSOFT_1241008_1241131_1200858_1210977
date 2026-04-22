package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.entities.MaintenanceTemplate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MaintenanceTemplateRepository implements JpaRepository<MaintenanceTemplate, Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends MaintenanceTemplate> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends MaintenanceTemplate> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<MaintenanceTemplate> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public MaintenanceTemplate getOne(Long aLong) {
        return null;
    }

    @Override
    public MaintenanceTemplate getById(Long aLong) {
        return null;
    }

    @Override
    public MaintenanceTemplate getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends MaintenanceTemplate> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends MaintenanceTemplate> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends MaintenanceTemplate> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends MaintenanceTemplate> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends MaintenanceTemplate> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends MaintenanceTemplate> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends MaintenanceTemplate, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends MaintenanceTemplate> S save(S entity) {
        return null;
    }

    @Override
    public <S extends MaintenanceTemplate> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<MaintenanceTemplate> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<MaintenanceTemplate> findAll() {
        return List.of();
    }

    @Override
    public List<MaintenanceTemplate> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MaintenanceTemplate entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MaintenanceTemplate> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<MaintenanceTemplate> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<MaintenanceTemplate> findAll(Pageable pageable) {
        return null;
    }
}
