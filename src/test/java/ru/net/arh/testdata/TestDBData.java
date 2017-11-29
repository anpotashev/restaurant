package ru.net.arh.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ru.net.arh.model.AbstractBaseEntity.START_SEQ;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDBData {
    public static final Dish DISH1 = new Dish(START_SEQ, "блюдо1");
    public static final Dish DISH2 = new Dish(START_SEQ + 1, "блюдо2");
    public static final Dish DISH3 = new Dish(START_SEQ + 2, "блюдо3");
    public static final Dish DISH4 = new Dish(START_SEQ + 3, "Блюдо4");
    public static final Map<Restaurant, List<Price>> PRICES = new HashMap<>();
    private static final int DISH_COUNT = 4;
    public static final Restaurant RESTAURANT1 = new Restaurant(START_SEQ + DISH_COUNT, "Ресторан1");
    public static final Restaurant RESTAURANT2 = new Restaurant(START_SEQ + DISH_COUNT + 1, "Ресторан2");
    public static final Restaurant RESTAURANT3 = new Restaurant(START_SEQ + DISH_COUNT + 2, "Ресторан3");
    public static final Restaurant RESTAURANT4 = new Restaurant(START_SEQ + DISH_COUNT + 3, "ресторан4");
    private static final int RESTAURANT_COUNT = 4;
    public static final User USER = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT, "User", "user@yandex.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT + 1, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);
    public static final User USER_ADMIN = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT + 2, "UserAdmin", "useradmin@hotmail.com", "useradmin_pwd", Role.ROLE_ADMIN, Role.ROLE_USER);
    private static final int USER_COUNT = 3;
    public static final int NEXT_ID = START_SEQ + DISH_COUNT + RESTAURANT_COUNT + USER_COUNT;

    static {
        createPrice(RESTAURANT1, DISH1, LocalDate.of(2017, 11, 9), 11.00);
        createPrice(RESTAURANT1, DISH2, LocalDate.of(2017, 11, 9), 11.00);
        createPrice(RESTAURANT1, DISH3, LocalDate.of(2017, 11, 9), 12.00);
        createPrice(RESTAURANT1, DISH2, LocalDate.of(2017, 11, 10), 10.11);
        createPrice(RESTAURANT1, DISH3, LocalDate.of(2017, 11, 10), 10.12);
        createPrice(RESTAURANT1, DISH4, LocalDate.of(2017, 11, 10), 10.13);
        createPrice(RESTAURANT2, DISH1, LocalDate.of(2017, 11, 9), 10.00);
        createPrice(RESTAURANT2, DISH4, LocalDate.of(2017, 11, 9), 11.00);
        createPrice(RESTAURANT2, DISH1, LocalDate.of(2017, 11, 10), 12.00);
        createPrice(RESTAURANT2, DISH2, LocalDate.of(2017, 11, 10), 14.00);
        createPrice(RESTAURANT2, DISH4, LocalDate.of(2017, 11, 10), 16.00);
    }

    private static void createPrice(Restaurant restaurant, Dish dish, LocalDate date, double price) {
        Price price1 = new Price(restaurant, dish, date, new BigDecimal(price));
        PRICES.computeIfAbsent(restaurant, k -> new LinkedList<>()).add(price1);
    }
}