package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Price;
import ru.net.arh.model.PriceId;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.AbstractBasedRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static ru.net.arh.model.Price.*;

@Slf4j
@Repository
public class PriceRepositoryImpl extends AbstractBasedRepository<Price, PriceId> {

    @Override
    public Class<Price> getClazz() {
        return Price.class;
    }

    @Override
    public String findAllNamedQuery() {
        return null;
    }

    @Transactional
    public void delete(int restaurant_id, int dish_id, LocalDate date) {
        Restaurant restaurant = em.getReference(Restaurant.class, restaurant_id);
        Dish dish = em.getReference(Dish.class, dish_id);
        this.delete(new PriceId(restaurant, dish, date));
    }

    @Transactional
    public Price update(int restaurant_id, int dish_id, LocalDate date, BigDecimal price) {
        Restaurant restaurant = em.getReference(Restaurant.class, restaurant_id);
        Dish dish = em.getReference(Dish.class, dish_id);
        return this.update(new Price(restaurant, dish, date, price));
    }

    @Transactional
    public Price update(int restaurant_id, int dish_id, LocalDate date, double price) {
        return this.update(restaurant_id, dish_id, date, price);
    }

    @Transactional
    public Price create(int restaurant_id, int dish_id, LocalDate date, BigDecimal price) {
        Restaurant restaurant = em.getReference(Restaurant.class, restaurant_id);
        Dish dish = em.getReference(Dish.class, dish_id);
        return this.create(new Price(restaurant, dish, date, price));
    }

    @Transactional
    public Price create(int restaurant_id, int dish_id, LocalDate date, double price) {
        return this.create(restaurant_id, dish_id, date, price);
    }

    public List<Price> getPricesOnTheDayInRestaurant(int restaurant_id, LocalDate date) {
        return em.createNamedQuery(PRICE_FIND_ALL_ON_DAY_IN_RESTAURANT)
                .setParameter("restaurant_id", restaurant_id)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Price> getPricesOnTheDay(LocalDate date) {
        return em.createNamedQuery(PRICE_FIND_ALL_ON_DAY)
                .setParameter("date", date)
                .getResultList();
    }

    public List<Price> getPricesOnTheDayWithFields(LocalDate date) {
        return em.createNamedQuery(PRICE_FIND_ALL_ON_DAY_WITH_FIELD)
                .setParameter("date", date)
                .getResultList();
    }
}
