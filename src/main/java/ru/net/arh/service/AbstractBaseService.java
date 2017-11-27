package ru.net.arh.service;

import ru.net.arh.model.AbstractBaseEntity;
import ru.net.arh.model.Dish;

import java.util.List;

public interface AbstractBaseService<T extends AbstractBaseEntity> {
    T get(int id);
    T save(T value);
    void delete(T value);
    List<T> getAll();
}
