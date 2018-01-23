package ru.net.arh.testdata;

import ru.net.arh.model.MenuItem;

import java.time.LocalDate;

import static ru.net.arh.testdata.TestDBData.*;

public class MenuItemTestData {

    public static final MenuItem NEW_MENU_ITEM = new MenuItem(RESTAURANT3, DISH1, LocalDate.now(), 10.00);
    public static final MenuItem DUPLICATE_MENU_ITEM = new MenuItem(RESTAURANT1, DISH3, LocalDate.now(), 10.00);
    public static final MenuItem UPDATE_MENU_ITEM = new MenuItem(START_PRICE_ID, RESTAURANT1, DISH2, LocalDate.now(), 22.33);

}
