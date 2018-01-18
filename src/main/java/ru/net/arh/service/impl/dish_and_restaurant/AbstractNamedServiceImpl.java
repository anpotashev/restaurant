package ru.net.arh.service.impl.dish_and_restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.repository.AbstractNamedBasedRepository;
import ru.net.arh.repository.DishRepository;
import ru.net.arh.repository.RestaurantRepository;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.DishService;
import ru.net.arh.service.RestaurantService;
import ru.net.arh.utils.validation.annotation.CheckForException;
import ru.net.arh.utils.validation.annotation.CheckForFalseResult;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;

import java.util.List;

@Service
public abstract class AbstractNamedServiceImpl<T extends NamedBasedEntity> implements AbstractNamedService<T> {

    protected abstract AbstractNamedBasedRepository<T> getRepository();

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @Override
    @Cacheable(cacheNames = "basecache", key = "{#root.targetClass.name, #id}")
    public T get(int id) {
        return getRepository().find(id);
    }

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @CheckForException(status = HttpStatus.NOT_FOUND)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "basecache", key = "{#root.targetClass.name}")
            , @CacheEvict(value = "basecache", key = "{#root.targetClass.name, #value.id}")
    })
    public T save(T value) {
        return getRepository().save(value);
    }

    @CheckForFalseResult(status = HttpStatus.NOT_FOUND)
    @Override
    @Caching(evict = {
            @CacheEvict(value = "basecache", key = "{#root.targetClass.name}")
            , @CacheEvict(value = "basecache", key = "{#root.targetClass.name, #id}")
    })
    public boolean delete(int id) {
        return getRepository().delete(id);
    }

    @Override
    @Cacheable(cacheNames = "basecache", key = "{#root.targetClass.name}")
    public List<T> getAll() {
        return getRepository().findAll();
    }

    public List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName) {
        return getRepository().findAllByFirstPartOfNameIgnoringCase(firstPartOfName);
    }

    @Service
    static class RestaurantServiceImpl extends AbstractNamedServiceImpl<Restaurant> implements RestaurantService {

        @Autowired
        private RestaurantRepository repository;

        @Override
        protected AbstractNamedBasedRepository<Restaurant> getRepository() {
            return repository;
        }
    }

    @Service
    static class DishServiceImpl extends AbstractNamedServiceImpl<Dish> implements DishService {

        @Autowired
        private DishRepository repository;

        @Override
        protected AbstractNamedBasedRepository<Dish> getRepository() {
            return repository;
        }
    }

}
