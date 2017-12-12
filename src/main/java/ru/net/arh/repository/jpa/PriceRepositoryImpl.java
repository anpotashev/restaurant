package ru.net.arh.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.key.PriceId;
import ru.net.arh.repository.PriceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class PriceRepositoryImpl implements PriceRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Price create(int restaurantId, int dishId, LocalDate date, double price) {
        if (checkExists(restaurantId, dishId, date))
            return null;
        return em.merge(getPrice(restaurantId, dishId, date, price));
    }

    @Override
    @Transactional
    public boolean delete(int restaurantId, int dishId, LocalDate date) {
        return em.createNamedQuery(NamedQueriesUtil.getDeleteNamedQuery(Price.class))
                .setParameter("restaurantId", restaurantId)
                .setParameter("dishId", dishId)
                .setParameter("date", date)
                .executeUpdate() > 0;
    }

    @Override
    public Price update(int restaurantId, int dishId, LocalDate date, double price) {
        if (!checkExists(restaurantId, dishId, date))
            return null;
        Price priceObject = getPrice(restaurantId, dishId, date, price);
        em.persist(priceObject);
        return priceObject;
    }

    @Override
    public List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date) {
        return em.createNamedQuery(Price.GET_ALL_FOR_RESTAURANT_FOR_DATE_QUERY_NAME)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .getResultList();
    }


    private PriceId getPriceId(int restaurantId, int dishId, LocalDate date) {
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        Dish dish = em.getReference(Dish.class, dishId);
        return new PriceId(restaurant, dish, date);
    }

    private Price getPrice(int restaurantId, int dishId, LocalDate date, double price) {
        PriceId priceId = getPriceId(restaurantId, dishId, date);
        return new Price(priceId, new BigDecimal(price));
    }

    private boolean checkExists(int restaurantId, int dishId, LocalDate date) {
        PriceId priceId = getPriceId(restaurantId, dishId, date);
        return em.find(Price.class, priceId) != null;
    }
}
