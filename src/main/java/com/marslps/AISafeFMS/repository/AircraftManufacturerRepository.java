package com.marslps.AISafeFMS.repository;

import com.marslps.AISafeFMS.model.vo.AircraftManufacturer;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AircraftManufacturerRepository implements JpaRepository<AircraftManufacturer, Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends AircraftManufacturer> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends AircraftManufacturer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<AircraftManufacturer> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public AircraftManufacturer getOne(Long aLong) {
        return null;
    }

    @Override
    public AircraftManufacturer getById(Long aLong) {
        return null;
    }

    @Override
    public AircraftManufacturer getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends AircraftManufacturer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends AircraftManufacturer> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends AircraftManufacturer> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends AircraftManufacturer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends AircraftManufacturer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends AircraftManufacturer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends AircraftManufacturer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends AircraftManufacturer> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AircraftManufacturer> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<AircraftManufacturer> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<AircraftManufacturer> findAll() {
        return List.of();
    }

    @Override
    public List<AircraftManufacturer> findAllById(Iterable<Long> longs) {
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
    public void delete(AircraftManufacturer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AircraftManufacturer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<AircraftManufacturer> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<AircraftManufacturer> findAll(Pageable pageable) {
        return null;
    }
}
