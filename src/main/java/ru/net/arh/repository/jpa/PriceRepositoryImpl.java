package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.PriceRepository;
import ru.net.arh.to.menu.MenuItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Repository
@Transactional(readOnly = true)
@PropertySource("classpath:db/db.properties")
public class PriceRepositoryImpl implements PriceRepository {

    @PersistenceContext
    private EntityManager em;

    @Value("${STATEMENT_BATCH_SIZE}")
    private int batchSize;

    @Override
    @Transactional
    public List<Price> create(LocalDate date, int restaurantId, MenuItem... menuItems) {
        List<Price> savedPrices = new LinkedList<>();
        int i = 0;
        for (MenuItem menuItem : menuItems) {
            Price price = getPrice(date, restaurantId, menuItem);
            savedPrices.add(em.merge(price));
            if (++i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        return savedPrices;
    }

    @Override
    @Transactional
    public List<Price> update(LocalDate date, int restaurantId, MenuItem... menuItems) {
        List<Price> savedPrices = new LinkedList<>();
        int i = 0;
        for (MenuItem menuItem : menuItems) {
            Price price = getPrice(date, restaurantId, menuItem);
            em.merge(price);
            savedPrices.add(price);
            if (++i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        return savedPrices;
    }

    private Price getPrice(LocalDate date, int restaurantId, MenuItem menuItem) {
        return getPrice(menuItem.getId(), restaurantId, menuItem.getDishId(), date, menuItem.getPrice());
    }

//    private Price persistOrMerge(Price price) {
//        if (price.isNew()) {
//            return em.merge(price);
//        } else {
//            em.persist(price);
//            return price;
//        }
//    }

    @Override
    @Transactional
    public boolean delete(final int id) {
        return em.createNamedQuery(NamedQueriesUtil.getDeleteNamedQuery(Price.class))
                .setParameter("id", id)
                .executeUpdate() > 0;
    }


    @Override
    public List<Price> getAllForRestorantInDay(final int restaurantId, final LocalDate date) {
        return em.createNamedQuery(Price.GET_ALL_FOR_RESTAURANT_FOR_DATE_QUERY_NAME)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .getResultList();
    }

    private Price getPrice(final Integer id, final int restaurantId, final int dishId, final LocalDate date, final double price) {
        Price result = getPrice(restaurantId, dishId, date, price);
        result.setId(id);
        return result;
    }

    private Price getPrice(final int restaurantId, final int dishId, final LocalDate date, final double price) {
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        Dish dish = em.getReference(Dish.class, dishId);
        return new Price(restaurant, dish, date, price);
    }


}
