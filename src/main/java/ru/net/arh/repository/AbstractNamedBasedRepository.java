package ru.net.arh.repository;

import ru.net.arh.model.mapped.NamedBasedEntity;

import java.util.List;

public interface AbstractNamedBasedRepository<T extends NamedBasedEntity> {

    T find(int id);

    T save(T value);

    boolean delete(int id);

    List<T> findAll();

    List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName);

}
