package ru.net.arh.service.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Price;
import ru.net.arh.repository.jpa.PriceRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PriceService {

    @Autowired
    private PriceRepositoryImpl repository;

    public Price create(int restaurant_id, int dish_id, LocalDate date, double price) {
        return repository.create(restaurant_id, dish_id, date, price);
    }

    public void delete(int restaurant_id, int dish_id, LocalDate date) {
        repository.delete(restaurant_id, dish_id, date);
    }

    public List<Price> getPricesOnTheDayInRestaurant(int restaurant_id, LocalDate date) {
        return repository.getPricesOnTheDayInRestaurant(restaurant_id, date);
    }

    public List<Price> getPricesOnTheDay(LocalDate date) {
        return repository.getPricesOnTheDay(date);
    }
}
