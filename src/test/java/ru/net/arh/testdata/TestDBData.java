package ru.net.arh.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.net.arh.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.net.arh.model.mapped.AbstractBaseEntity.START_SEQ;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDBData {

    private static final int PRICE_COUNT = 11;
    public static int NEXT_ID = START_SEQ;
    public static final Dish DISH1 = new Dish(NEXT_ID++, "блюдо1");
    public static final Dish DISH2 = new Dish(NEXT_ID++, "блюдо2");
    public static final Dish DISH3 = new Dish(NEXT_ID++, "блюдо3");
    public static final Map<Restaurant, List<Price>> PRICES = new HashMap<>();
    //    private static final int DISH_COUNT = 4;
    public static final Dish DISH4 = new Dish(NEXT_ID++, "ББлюдо4");
    public static final int START_RESTAURANT_ID = NEXT_ID;
    public static final Restaurant RESTAURANT1 = new Restaurant(NEXT_ID++, "Ресторан1");
    public static final Restaurant RESTAURANT2 = new Restaurant(NEXT_ID++, "Ресторан2");
    public static final Restaurant RESTAURANT3 = new Restaurant(NEXT_ID++, "Ресторан3");
    //    private static final int RESTAURANT_COUNT = 4;
    public static final Restaurant RESTAURANT4 = new Restaurant(NEXT_ID++, "Рресторан4");
    public static final User USER = new User(NEXT_ID++, "User", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(NEXT_ID++, "Admin", "admin", Role.ROLE_ADMIN);
    public static final User USER_ADMIN = new User(NEXT_ID++, "useradmin@hotmail.com", "useradmin_pwd", Role.ROLE_ADMIN, Role.ROLE_USER);
    public static final User USER2 = new User(NEXT_ID++, "User2", "password2", Role.ROLE_USER);
    public static final User USER3 = new User(NEXT_ID++, "User3", "password3", Role.ROLE_USER);
    public static final int START_PRICE_ID = NEXT_ID;
    public static final Price PRICE1 = new Price(NEXT_ID++, RESTAURANT1, DISH2, LocalDate.now(), 10.11);
    public static final Price PRICE2 = new Price(NEXT_ID++, RESTAURANT1, DISH3, LocalDate.now(), 10.12);
    public static final Price PRICE3 = new Price(NEXT_ID++, RESTAURANT1, DISH4, LocalDate.now(), 10.13);
    public static final Price PRICE4 = new Price(NEXT_ID++, RESTAURANT2, DISH1, LocalDate.now(), 12.00);
    public static final Price PRICE5 = new Price(NEXT_ID++, RESTAURANT2, DISH2, LocalDate.now(), 14.00);
    public static final Price PRICE6 = new Price(NEXT_ID++, RESTAURANT2, DISH4, LocalDate.now(), 16.00);
    public static final int START_YESTERDAY_PRICE_ID = NEXT_ID;
    public static final Price PRICE7 = new Price(NEXT_ID++, RESTAURANT1, DISH1, LocalDate.now().minusDays(1), 10.00);
    public static final Price PRICE8 = new Price(NEXT_ID++, RESTAURANT1, DISH2, LocalDate.now().minusDays(1), 11.00);
    public static final Price PRICE9 = new Price(NEXT_ID++, RESTAURANT1, DISH3, LocalDate.now().minusDays(1), 12.00);
    public static final Price PRICE10 = new Price(NEXT_ID++, RESTAURANT2, DISH1, LocalDate.now().minusDays(1), 10.00);
    public static final Price PRICE11 = new Price(NEXT_ID++, RESTAURANT2, DISH4, LocalDate.now().minusDays(1), 11.00);

    public static final Vote VOTE1 = new Vote(USER, LocalDate.now(), RESTAURANT1);
    public static final Vote VOTE2 = new Vote(USER_ADMIN, LocalDate.now(), RESTAURANT2);
    public static final Vote VOTE3 = new Vote(USER2, LocalDate.now(), RESTAURANT1);
    public static final Vote VOTE1_YESTERDAY = new Vote(USER, LocalDate.now().minusDays(1), RESTAURANT1);
    public static final Vote VOTE2_YESTERDAY = new Vote(USER_ADMIN, LocalDate.now().minusDays(1), RESTAURANT2);

    public static final int TODAY_RESTAURANT1_COUNT = 2;
    public static final int YESTERDAY_RESTAURANT1_COUNT = 1;

}
