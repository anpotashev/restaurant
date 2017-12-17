package ru.net.arh.repository;

import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository {

//    Price create(LocalDate date, int restaurantId, int dishId, double price);

    boolean delete(final int id);

//    Price update(final LocalDate date, final int id, final int restaurantId, final int dishId, final double price);

    List<Price> getAllForRestorantInDay(final int restaurantId, final LocalDate date);

    List<Price> create(final LocalDate date, final int restaurantId, MenuItem... menuItems);

    List<Price> update(final LocalDate date, final int restaurantId, MenuItem... menuItems);

}
