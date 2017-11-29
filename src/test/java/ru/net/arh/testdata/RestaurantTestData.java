package ru.net.arh.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Restaurant;

import static ru.net.arh.testdata.TestDBData.START_RESTAURANT_ID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantTestData {

    public static final Restaurant RESTAURANT_UPDATED = new Restaurant(START_RESTAURANT_ID, "ресторан1_измененный");
    public static final Restaurant NEW_RESTAURANT = new Restaurant(TestDBData.NEXT_ID, "ресторан5");

    public static Restaurant newRestaurant() {
        return new Restaurant("ресторан5");
    }

}
