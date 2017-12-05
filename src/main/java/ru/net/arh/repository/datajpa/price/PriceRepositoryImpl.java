package ru.net.arh.repository.datajpa.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Price;
import ru.net.arh.model.PriceId;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.PriceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    @Autowired
    private DatajpaPriceReporsitory reporsitory;
    @Autowired
    private JpaRepository<Restaurant, Integer> restaurantRepository;
    @Autowired
    private JpaRepository<Dish, Integer> dishRepository;

    @Override
    public Price create(int restaurantId, int dishId, LocalDate date, double price) {
        return checkExist(restaurantId, dishId, date) ? null : reporsitory.save(createPrice(restaurantId, dishId, date, price));
    }

    @Override
    public boolean delete(int restaurantId, int dishId, LocalDate date) {
        return reporsitory.deleteByKey(createPriceId(restaurantId, dishId, date)) > 0;
    }

    @Override
    public Price update(int restaurantId, int dishId, LocalDate date, double price) {
        return !checkExist(restaurantId, dishId, date) ? null : reporsitory.save(createPrice(restaurantId, dishId, date, price));
    }

    @Override
    public List<Price> getDayPricesWithFields(LocalDate date) {
        return reporsitory.findAllByKeyDate(date);
    }

    private boolean checkExist(int restauratnId, int dishId, LocalDate date) {
        return reporsitory.findByKey(createPriceId(restauratnId, dishId, date)) != null;
    }

    private Price createPrice(int restaurantId, int dishId, LocalDate date, double price) {
        return new Price(createPriceId(restaurantId, dishId, date), new BigDecimal(price));
    }

    private PriceId createPriceId(int restaurantId, int dishId, LocalDate date) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        Dish dish = dishRepository.getOne(dishId);
        return new PriceId(restaurant, dish, date);
    }
}
