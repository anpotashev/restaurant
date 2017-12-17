package ru.net.arh.service;

import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

//    Price create( LocalDate date, int restaurantId, int dishId, double price);

//    Price update(LocalDate date, int id, int restaurantId, int dishId, double price);

    boolean delete(LocalDate date, int id);

    List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date);

    //two different methods for create and update, because of using arrays:
    // If array menuItems contains values with null and not null "id" should be an exception.
    List<Price> create(LocalDate date, int restaurantId, MenuItem... menuItems);

    List<Price> update(LocalDate date, int restaurantId, MenuItem... menuItems);
}
