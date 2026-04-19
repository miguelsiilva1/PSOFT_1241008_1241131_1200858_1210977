package com.marslps.AISafeFMS.security.repository;

import com.marslps.AISafeFMS.security.model.entities.SystemUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class SystemUserRepository implements JpaRepository<SystemUser, Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends SystemUser> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends SystemUser> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<SystemUser> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public SystemUser getOne(Long aLong) {
        return null;
    }

    @Override
    public SystemUser getById(Long aLong) {
        return null;
    }

    @Override
    public SystemUser getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends SystemUser> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends SystemUser> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends SystemUser> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends SystemUser> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends SystemUser> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends SystemUser> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends SystemUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends SystemUser> S save(S entity) {
        return null;
    }

    @Override
    public <S extends SystemUser> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<SystemUser> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<SystemUser> findAll() {
        return List.of();
    }

    @Override
    public List<SystemUser> findAllById(Iterable<Long> longs) {
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
    public void delete(SystemUser entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends SystemUser> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<SystemUser> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<SystemUser> findAll(Pageable pageable) {
        return null;
    }
}
