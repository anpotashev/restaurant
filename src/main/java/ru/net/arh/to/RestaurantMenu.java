package ru.net.arh.to;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class RestaurantMenu {

    private final Restaurant restaurant;
    private final List<RestaurantMenuItem> menuItems = new LinkedList<>();


    public RestaurantMenu(Restaurant restaurant, List<Price> prices) {
        this.restaurant = restaurant;
        prices.forEach(p -> menuItems.add(new RestaurantMenuItem(p)));
    }
}
