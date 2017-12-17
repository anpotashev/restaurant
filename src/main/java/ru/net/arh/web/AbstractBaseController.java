package ru.net.arh.web;

import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.service.AbstractBaseService;

import java.util.List;

public abstract class AbstractBaseController<T extends AbstractBaseEntity> {

    protected abstract AbstractBaseService<T> getService();

    public T get(int key) {
        return getService().get(key);
    }

    public List<T> getAll() {
        return getService().getAll();
    }

    public T save(T value) {
        if (value.isNew()) {
            return getService().create(value);
        }
        return getService().update(value);

    }

    public void delete(int key) {
        getService().delete(key);
    }

}
