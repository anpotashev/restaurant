package ru.net.arh.service.abstractimpl;

import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.repository.AbstractBasedRepository;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.utils.aop.annotation.NeedValidateReturnValueForNullOrFalse;

import java.util.List;

public abstract class AbstractBaseServiceImpl<T extends AbstractBaseEntity> implements AbstractBaseService<T> {

    protected abstract AbstractBasedRepository<T> getRepository();

    @Override
    @NeedValidateReturnValueForNullOrFalse
    public T get(int id) {
        return getRepository().find(id);
    }

    @Override
    @NeedValidateReturnValueForNullOrFalse
    public T update(T value) {
        return getRepository().update(value);
    }

    @Override
    public T create(T value) {
        return getRepository().create(value);
    }

    @Override
    @NeedValidateReturnValueForNullOrFalse
    public boolean delete(int id) {
        return getRepository().delete(id);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }
}
