package ru.net.arh.service;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.AbstractBaseEntity;
import ru.net.arh.repository.AbstractBasedRepository;

import java.util.List;

import static ru.net.arh.utils.ValidationUnit.checkNotFound;
import static ru.net.arh.utils.ValidationUnit.checkNotFoundWithKey;

@Slf4j
public abstract class AbstractBaseServiceImpl<T extends AbstractBaseEntity> implements AbstractBaseService<T> {

    protected abstract AbstractBasedRepository<T, Integer> getRepository();

    @Override
    public T get(int key) {
        return checkNotFound(getRepository().find(key), key);
    }

    @Override
    public T update(T value) {
        return checkNotFound(getRepository().update(value), value.getKey());
    }

    @Override
    public T create(T value) {
        return getRepository().create(value);
    }

    @Override
    public void delete(int key) {
        checkNotFoundWithKey(getRepository().delete(key), key);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }
}
