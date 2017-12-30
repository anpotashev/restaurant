package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.net.arh.model.Price;
import ru.net.arh.to.menu.MenuItem;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuUtil {

    public static List<MenuItem> convertToMenuItems(List<Price> prices) {
        return prices.stream()
                .map(MenuUtil::convertToMenuItem)
                .collect(Collectors.toList());
    }

    public static MenuItem convertToMenuItem(Price price) {
        return new MenuItem(price.getId(), price.getDish().getId(), price.getDish().getName(), price.getPrice().doubleValue());
    }
}
