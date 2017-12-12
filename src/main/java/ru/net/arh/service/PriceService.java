package ru.net.arh.service;

import ru.net.arh.model.Price;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

    Price create(int restaurantId, int dishId, LocalDate date, double price);

    void delete(int restaurantId, int dishId, LocalDate date);

    Price update(int restaurantId, int dishId, LocalDate date, double price);

    List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date);

}
