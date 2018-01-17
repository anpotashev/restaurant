package ru.net.arh.service.impl.abstractimpl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.repository.AbstractBasedRepository;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.utils.validation.annotation.CheckForException;
import ru.net.arh.utils.validation.annotation.CheckForFalseResult;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;

import java.util.List;

public abstract class AbstractBaseServiceImpl<T extends AbstractBaseEntity> implements AbstractBaseService<T> {

    protected abstract AbstractBasedRepository<T> getRepository();

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @Override
    @Cacheable(cacheNames = "basecache", key = "{#id}")
    public T get(int id) {
        return getRepository().find(id);
    }

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @CheckForException(status = HttpStatus.NOT_FOUND)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "basecache", key = "{#root.targetClass.name}")
            , @CacheEvict(value = "basecache", key = "{#value.id}")
    })
    public T save(final T value) {
        return getRepository().save(value);
    }

    @CheckForFalseResult(status = HttpStatus.NOT_FOUND)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "basecache", key = "{#root.targetClass.name}")
            , @CacheEvict(value = "basecache", key = "{#id}")
    })
    public boolean delete(int id) {
        return getRepository().delete(id);
    }

    @Override
    @Cacheable(cacheNames = "basecache", key = "{#root.targetClass.name}")
    public List<T> getAll() {
        return getRepository().findAll();
    }

}
