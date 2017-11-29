package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.NamedBasedRepository;

@Slf4j
@Repository
public class RestaurantRepositoryImpl extends NamedBasedRepository<Restaurant> {

    @Override
    public Class<Restaurant> getClazz() {
        return Restaurant.class;
    }

    @Override
    public String findAllNamedQuery() {
        return Restaurant.FIND_ALL;
    }

    @Override
    protected String findByNameNamedQueryIgnoreCase() {
        return Restaurant.FIND_BY_NAME_IGNORE_CASE;
    }

    @Override
    protected String findByNameNamedQuery() {
        return Restaurant.FIND_BY_NAME;
    }
}
