package com.kks.demo.test;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class testingRepository implements JpaRepository<testingEntity, Integer> {
    @Override
    public List<testingEntity> findAll() {
        return null;
    }

    @Override
    public List<testingEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<testingEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<testingEntity> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(testingEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends testingEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends testingEntity> S save(S entity) {
        return null;
    }

    @Override
    public <S extends testingEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<testingEntity> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends testingEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends testingEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<testingEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public testingEntity getOne(Integer integer) {
        return null;
    }

    @Override
    public testingEntity getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends testingEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends testingEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends testingEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends testingEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends testingEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends testingEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends testingEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
