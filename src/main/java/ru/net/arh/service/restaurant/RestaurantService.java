package ru.net.arh.service.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;
import ru.net.arh.service.AbstractBaseService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class RestaurantService implements AbstractBaseService<Restaurant> {
//    @Autowired
//    private RestaurantRepository dishRepository;

    public Restaurant get(int id) {
        return null;
    }

    public Restaurant save(Restaurant dish) {
        return null;
    }

    public void delete(Restaurant dish) {
    }

    public List<Restaurant> getAll() {
        return null;
    }

    public List<Price> getPricesOnTheDay(int restaurant_id, LocalDate date) {
        return null;
    }

    public boolean voteRestaurant(int restaurant_id, int id, LocalDateTime dateTime) {
        return false;
    }
}
