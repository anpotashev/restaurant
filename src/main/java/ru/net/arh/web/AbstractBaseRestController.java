package ru.net.arh.web;

import ru.net.arh.model.AbstractBaseEntity;
import ru.net.arh.service.AbstractBaseService;

import java.util.List;

public abstract class AbstractBaseRestController<T extends AbstractBaseEntity> {

    protected abstract AbstractBaseService<T> getBaseService();

    public T get(int key) {
        return getBaseService().get(key);
    }

    public List<T> getAll() {
        return getBaseService().getAll();
    }

    public T save(T value) {
        return getBaseService().update(value);
    }

    public void delete(int key) {
        getBaseService().delete(key);
    }

}
