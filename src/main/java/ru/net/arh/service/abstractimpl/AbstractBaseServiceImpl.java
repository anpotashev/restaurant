package ru.net.arh.service.abstractimpl;

import org.springframework.http.HttpStatus;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.repository.AbstractBasedRepository;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.utils.validation.annotation.CheckForFalseResult;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;

import java.util.List;

public abstract class AbstractBaseServiceImpl<T extends AbstractBaseEntity> implements AbstractBaseService<T> {

    protected abstract AbstractBasedRepository<T> getRepository();

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @Override
    public T get(int id) {
        return getRepository().find(id);
    }

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @Override
    public T save(final T value) {
        return getRepository().save(value);
    }

//    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
//    @Override
//    public T create(final T value) {
//        return getRepository().create(value);
//    }

    @CheckForFalseResult(status = HttpStatus.NOT_FOUND)
    @Override
    public boolean delete(int id) {
        return getRepository().delete(id);
    }

    @Override
    public List<T> getAll() {
        return getRepository().findAll();
    }

}
