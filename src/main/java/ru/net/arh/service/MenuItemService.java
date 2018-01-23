package ru.net.arh.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.net.arh.model.MenuItem;
import ru.net.arh.to.menu.MenuItemTo;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean delete(LocalDate date, int restaurantId, int id);

    List<MenuItemTo> getAllForRestorantInDay(LocalDate date, int restaurantId);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    MenuItem save(LocalDate date, int restaurantId, MenuItemTo menuItemTo);

    MenuItemTo get(LocalDate date, int restaurantId, int priceId);
}
