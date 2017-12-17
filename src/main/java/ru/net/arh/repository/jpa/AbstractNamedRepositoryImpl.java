package ru.net.arh.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.repository.AbstractNamedBasedRepository;
import ru.net.arh.repository.DishRepository;
import ru.net.arh.repository.RestaurantRepository;

import java.util.List;

@Repository
public abstract class AbstractNamedRepositoryImpl<T extends NamedBasedEntity> extends AbstractBasedRepositoryImpl<T> implements AbstractNamedBasedRepository<T> {

    @Override
    public List<T> findAllByFirstPartOfNameIgnoringCase(final String firstPartOfName) {
        String findByFirstPartNamedQueryName = NamedQueriesUtil.getFindAllByFirstPartOfNameNamedQuery(getEntityClass());
        return em.createNamedQuery(findByFirstPartNamedQueryName)
                .setParameter("firstPartOfName", firstPartOfName)
                .getResultList();
    }

    @Override
    protected abstract Class<T> getEntityClass();

    @Repository
    public static class DishRepositoryImpl extends AbstractNamedRepositoryImpl<Dish> implements DishRepository {
        @Override
        protected Class getEntityClass() {
            return Dish.class;
        }
    }

    @Repository
    public static class RestaurantRepositoryImpl extends AbstractNamedRepositoryImpl<Restaurant> implements RestaurantRepository {
        @Override
        protected Class getEntityClass() {
            return Restaurant.class;
        }
    }

}
