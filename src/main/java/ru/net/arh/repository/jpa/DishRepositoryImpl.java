package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.NamedBasedRepository;

@Slf4j
@Repository
public class DishRepositoryImpl extends NamedBasedRepository<Dish> {

    @Override
    public Class<Dish> getClazz() {
        return Dish.class;
    }

    @Override
    public String findAllNamedQuery() {
        return Dish.FIND_ALL;
    }

    @Override
    protected String findByNameNamedQueryIgnoreCase() {
        return Dish.FIND_BY_NAME_IGNORE_CASE;
    }

    @Override
    protected String findByNameNamedQuery() {
        return Dish.FIND_BY_NAME;
    }
}
