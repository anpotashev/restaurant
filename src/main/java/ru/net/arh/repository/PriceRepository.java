package ru.net.arh.repository;

import ru.net.arh.model.Price;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository {
    Price create(int restaurantId, int dishId, LocalDate date, double price);

    boolean delete(int restaurantId, int dishId, LocalDate date);

    Price update(int restaurantId, int dishId, LocalDate date, double price);

    List<Price> getDayPricesWithFields(LocalDate date);
}
