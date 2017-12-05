package ru.net.arh.service.price;

import ru.net.arh.model.Price;
import ru.net.arh.to.menu.DayMenu;

import java.time.LocalDate;

public interface PriceService {

    Price create(int restaurantId, int dishId, LocalDate date, double price);

    void delete(int restaurantId, int dishId, LocalDate date);

    Price update(int restaurantId, int dishId, LocalDate date, double price);

    public DayMenu getDayMenu();
}
