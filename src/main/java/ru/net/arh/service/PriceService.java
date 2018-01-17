package ru.net.arh.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean delete(LocalDate date, int id, int restaurantId);

    List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Price save(LocalDate date, int restaurantId, MenuItem menuItem);

    Price get(int priceId, int restaurantId, LocalDate date);
}
