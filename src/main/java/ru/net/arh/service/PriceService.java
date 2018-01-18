package ru.net.arh.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean delete(LocalDate date, int restaurantId, int id);

    List<MenuItem> getAllForRestorantInDay(LocalDate date, int restaurantId);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Price save(LocalDate date, int restaurantId, MenuItem menuItem);

    MenuItem get(LocalDate date, int restaurantId, int priceId);
}
