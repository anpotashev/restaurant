package ru.net.arh.repository;

import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository {

    boolean delete(LocalDate date, int id, int restaurantId);

    List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date);

    Price save(LocalDate date, int restaurantId, MenuItem menuItem);

    Price get(int id, int restaurantId, LocalDate date);
}
