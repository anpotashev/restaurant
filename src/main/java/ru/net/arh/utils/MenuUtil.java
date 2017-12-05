package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;
import ru.net.arh.to.menu.DayMenu;
import ru.net.arh.to.menu.RestaurantMenuItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuUtil {

    public static DayMenu convert(LocalDate date, List<Price> prices) {

        Map<Restaurant, List<RestaurantMenuItem>> collect = prices.stream()
                .collect(Collectors.groupingBy(MenuUtil::priceToRestaurant, Collectors.mapping(RestaurantMenuItem::new, Collectors.toList())));
        return new DayMenu(date, collect);
    }

    private static Restaurant priceToRestaurant(Price price) {
        return price.getKey().getRestaurant();
    }


}
