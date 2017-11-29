package ru.net.arh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.net.arh.model.NamedBasedEntity;
import ru.net.arh.repository.NamedBasedRepository;
import ru.net.arh.utils.ValidationUnit;

import java.util.List;

@Slf4j
public abstract class AbstractNamedService<T extends NamedBasedEntity> implements AbstractBaseService<T> {

    protected abstract NamedBasedRepository<T> getRepository();

    public T get(int id) {
        return ValidationUnit.checkNotFound(getRepository().find(id), id);
    }

    public T create(T value) {
        return getRepository().create(value);
    }

    public T update(T value) {
        return ValidationUnit.checkNotFound(getRepository().update(value), value.getKey());
    }

    public void delete(int id) {
        try {
            getRepository().delete(id);
        } catch (EmptyResultDataAccessException e) {
            ValidationUnit.checkNotFound(null, id);
        }
    }

    public List<T> getAll() {
        return getRepository().findAll();
    }

    public List<T> findStaringWithName(String name) {
        return getRepository().findByName(name);
    }

    public List<T> findStaringWithNameIgnoreCase(String name) {
        return getRepository().findByNameIgnoreCase(name);
    }
}
