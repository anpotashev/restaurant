package ru.net.arh.repository.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.PriceRepository;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
@Transactional(readOnly = true)
@PropertySource("classpath:db/db.properties")
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private DataJpaPriceRepository priceRepository;
    @Autowired
    private DataJpaRestaurantRepository restaurantRepository;
    @Autowired
    private DataJpaDishRepository dishRepository;

    @Override
    @Transactional
    public Price save(LocalDate date, int restaurantId, MenuItem menuItem) {
        Price price = getPrice(date, restaurantId, menuItem);
        return priceRepository.save(price);
    }

    @Override
    public Price get(int id, int restaurantId, LocalDate date) {
        return priceRepository.getByIdAndRestaurantIdAndDate(id, restaurantId, date).orElse(null);
    }

    private Price getPrice(LocalDate date, int restaurantId, MenuItem menuItem) {
        return getPrice(menuItem.getId(), restaurantId, menuItem.getDishId(), date, menuItem.getPrice());
    }

    @Override
    @Transactional
    public boolean delete(final int id) {
        return priceRepository.deleteById(id) > 0;
    }


    @Override
    public List<Price> getAllForRestorantInDay(final int restaurantId, final LocalDate date) {
        return priceRepository.getAllByRestaurantIdAndDate(restaurantId, date);
    }

    private Price getPrice(final Integer id, final int restaurantId, final int dishId, final LocalDate date, final double price) {
        Price result = getPrice(restaurantId, dishId, date, price);
        result.setId(id);
        return result;
    }

    private Price getPrice(final int restaurantId, final int dishId, final LocalDate date, final double price) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        Dish dish = dishRepository.getOne(dishId);
        return new Price(restaurant, dish, date, price);
    }

}
