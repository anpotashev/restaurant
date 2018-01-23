package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.net.arh.model.MenuItem;
import ru.net.arh.to.menu.MenuItemTo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuUtil {

    public static List<MenuItemTo> convertToMenuItemTos(List<MenuItem> menuItems) {
        return menuItems.stream()
                .map(MenuUtil::convertToMenuItemTo)
                .collect(Collectors.toList());
    }

    public static MenuItemTo convertToMenuItemTo(MenuItem menuItem) {
        return menuItem == null ? null :
                new MenuItemTo(menuItem.getId(), menuItem.getDish().getId(), menuItem.getDish().getName(), menuItem.getPrice().doubleValue());
    }

    public static List<MenuItemTo> convertToMenuItemTos(MenuItem... menuItems) {
        return convertToMenuItemTos(Arrays.asList(menuItems));
    }
}
