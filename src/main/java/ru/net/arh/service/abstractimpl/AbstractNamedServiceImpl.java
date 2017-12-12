package ru.net.arh.service.abstractimpl;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Service
public abstract class AbstractNamedServiceImpl<T extends NamedBasedEntity> extends AbstractBaseServiceImpl<T> implements AbstractNamedService<T> {

    protected abstract AbstractNamedBasedRepository<T> getRepository();

    @Override
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
