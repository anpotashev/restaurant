package ru.net.arh.repository;

import java.util.List;

public interface AbstractBasedRepository<T> {

    T find(final int id);

    T create(final T value);

    T update(final T value);

    boolean delete(final int id);

    List<T> findAll();
}