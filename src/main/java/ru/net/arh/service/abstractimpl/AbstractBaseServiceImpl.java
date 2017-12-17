package ru.net.arh.service.abstractimpl;

import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.repository.AbstractBasedRepository;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.utils.aop.annotation.Throw404IfFalseResult;
import ru.net.arh.utils.aop.annotation.Throw404IfNullResult;

import java.util.List;

public abstract class AbstractBaseServiceImpl<T extends AbstractBaseEntity> implements AbstractBaseService<T> {

    protected abstract AbstractBasedRepository<T> getRepository();

    @Override
    @Throw404IfNullResult
    public T get(int id) {
        return getRepository().find(id);
    }

    @Override
    @Throw404IfNullResult
    public T update(final T value) {
        return getRepository().update(value);
    }

    @Override
    public T create(final T value) {
        return getRepository().create(value);
    }

    @Override
    @Throw404IfFalseResult
    public boolean delete(int id) {
        return getRepository().delete(id);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }
}
