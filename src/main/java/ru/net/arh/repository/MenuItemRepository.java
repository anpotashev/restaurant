package ru.net.arh.repository;

import ru.net.arh.model.MenuItem;
import ru.net.arh.to.menu.MenuItemTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository {

    boolean delete(LocalDate date, int id, int restaurantId);

    List<MenuItem> getAllForRestorantInDay(int restaurantId, LocalDate date);

    MenuItem save(LocalDate date, int restaurantId, MenuItemTo menuItemTo);

    MenuItem get(int id, int restaurantId, LocalDate date);
}
