package ru.net.arh.web.controller;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.service.AbstractNamedService;

import java.util.List;

@Slf4j
public abstract class AbstractNamedController<T extends NamedBasedEntity> {

    protected abstract AbstractNamedService<T> getService();

    public T get(int key) {
        return getService().get(key);
    }

    public List<T> getAll() {
        return getService().getAll();
    }

    public T save(T value) {
        return getService().save(value);
    }

    public void delete(int key) {
        getService().delete(key);
    }

    public List<T> getByFirstPartOfName(String firstPartOfName) {
        return getService().findAllByFirstPartOfNameIgnoringCase(firstPartOfName);
    }

}
