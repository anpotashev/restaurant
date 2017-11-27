package ru.net.arh.web;

import ru.net.arh.model.AbstractBaseEntity;
import ru.net.arh.service.AbstractBaseService;

import java.util.List;

public abstract class AbstractBaseRestController<T extends AbstractBaseEntity> {

    protected abstract AbstractBaseService<T> getBaseService();

    public T get(int id) {
        return getBaseService().get(id);
    }

    public List<T> getAll() {
        return getBaseService().getAll();
    }

    public T save(T value) {
        return getBaseService().save(value);
    }

    public void delete(T value) {
        getBaseService().delete(value);
    }

}
