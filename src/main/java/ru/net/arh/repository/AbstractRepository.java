package ru.net.arh.repository;

import java.util.List;

public interface AbstractRepository<T> {

    T save(T value);
    List<T> getAll();
    boolean delete(T value);
}
