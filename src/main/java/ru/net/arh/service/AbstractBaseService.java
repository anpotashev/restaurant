package ru.net.arh.service;

import ru.net.arh.model.AbstractBaseEntity;

import java.util.List;

public interface AbstractBaseService<T extends AbstractBaseEntity> {
    T get(int key);

    T update(T value);

    T create(T value);

    void delete(int key);

    List<T> getAll();
}
