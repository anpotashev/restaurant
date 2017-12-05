package ru.net.arh.to.menu;

import lombok.Getter;
import ru.net.arh.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
//@ToString
public class DayMenu {
    private final LocalDate date;
    private final Map<Restaurant, List<RestaurantMenuItem>> restarauntMenu;

    public DayMenu(LocalDate date, Map<Restaurant, List<RestaurantMenuItem>> restarauntMenu) {
        this.date = date;
        this.restarauntMenu = restarauntMenu;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(date.toString());
        restarauntMenu.forEach((restaurant, restaurantMenuItems) -> {
            builder.append("\n")
                    .append(restaurant.getKey())
                    .append(" - ")
                    .append(restaurant.getName());
            restaurantMenuItems.forEach(restaurantMenuItem -> {
                builder.append("\n")
                        .append(restaurantMenuItem.getDish().getKey())
                        .append(" - ")
                        .append(restaurantMenuItem.getDish().getName())
                        .append(" - ")
                        .append(restaurantMenuItem.getPrice());
            });
        });
        return builder.toString();
    }
}
