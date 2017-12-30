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

import static ru.net.arh.model.mapped.AbstractBaseEntity.START_SEQ;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDBData {

    public static final Dish DISH1 = new Dish(START_SEQ, "блюдо1");
    public static final Dish DISH2 = new Dish(START_SEQ + 1, "блюдо2");
    public static final Dish DISH3 = new Dish(START_SEQ + 2, "блюдо3");
    public static final Dish DISH4 = new Dish(START_SEQ + 3, "Блюдо4");
    public static final Map<Restaurant, List<Price>> PRICES = new HashMap<>();
    private static final int DISH_COUNT = 4;

    public static final int START_RESTAURANT_ID = START_SEQ + DISH_COUNT;

    public static final Restaurant RESTAURANT1 = new Restaurant(START_RESTAURANT_ID, "Ресторан1");
    public static final Restaurant RESTAURANT2 = new Restaurant(START_RESTAURANT_ID + 1, "Ресторан2");
    public static final Restaurant RESTAURANT3 = new Restaurant(START_RESTAURANT_ID + 2, "Ресторан3");
    public static final Restaurant RESTAURANT4 = new Restaurant(START_RESTAURANT_ID + 3, "ресторан4");
    private static final int RESTAURANT_COUNT = 4;

    public static final User USER = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT, "User", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT + 1, "Admin", "admin", Role.ROLE_ADMIN);
    public static final User USER_ADMIN = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT + 2, "useradmin@hotmail.com", "useradmin_pwd", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER2 = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT + 3, "User2", "password2", Role.ROLE_USER);
    public static final User USER3 = new User(START_SEQ + DISH_COUNT + RESTAURANT_COUNT + 4, "User3", "password3", Role.ROLE_USER);


    private static final int USER_COUNT = 5;

    public static final int NEXT_ID = START_SEQ + DISH_COUNT + RESTAURANT_COUNT + USER_COUNT;

    static {
        createPrice(RESTAURANT1, DISH1, LocalDate.now().minusDays(1), 11.00);
        createPrice(RESTAURANT1, DISH2, LocalDate.now().minusDays(1), 11.00);
        createPrice(RESTAURANT1, DISH3, LocalDate.now().minusDays(1), 12.00);
        createPrice(RESTAURANT1, DISH2, LocalDate.now(), 10.11);
        createPrice(RESTAURANT1, DISH3, LocalDate.now(), 10.12);
        createPrice(RESTAURANT1, DISH4, LocalDate.now(), 10.13);
        createPrice(RESTAURANT2, DISH1, LocalDate.now().minusDays(1), 10.00);
        createPrice(RESTAURANT2, DISH4, LocalDate.now().minusDays(1), 11.00);
        createPrice(RESTAURANT2, DISH1, LocalDate.now(), 12.00);
        createPrice(RESTAURANT2, DISH2, LocalDate.now(), 14.00);
        createPrice(RESTAURANT2, DISH4, LocalDate.now(), 16.00);
    }

    private static void createPrice(Restaurant restaurant, Dish dish, LocalDate date, double price) {
        Price price1 = new Price(restaurant, dish, date, new BigDecimal(price));
        PRICES.computeIfAbsent(restaurant, k -> new LinkedList<>()).add(price1);
    }

    public static final Vote VOTE1 = new Vote(USER, LocalDate.now(), RESTAURANT1);
    public static final Vote VOTE2 = new Vote(USER_ADMIN, LocalDate.now(), RESTAURANT2);
    public static final Vote VOTE3 = new Vote(USER2, LocalDate.now(), RESTAURANT1);
    public static final Vote VOTE1_YESTERDAY = new Vote(USER, LocalDate.now().minusDays(1), RESTAURANT1);
    public static final Vote VOTE2_YESTERDAY = new Vote(USER_ADMIN, LocalDate.now().minusDays(1), RESTAURANT2);

}
