package ru.net.arh.repository;

import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface PriceRepository {

    boolean delete(final int id);

    List<Price> getAllForRestorantInDay(final int restaurantId, final LocalDate date);

    Price save(final LocalDate date, final int restaurantId, MenuItem menuItem);

    Price get(int id, int restaurantId, LocalDate date);
}
