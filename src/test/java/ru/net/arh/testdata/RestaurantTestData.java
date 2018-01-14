package ru.net.arh.testdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.net.arh.model.Restaurant;

import java.util.Arrays;
import java.util.List;

import static ru.net.arh.testdata.TestDBData.*;

@Slf4j
//@NoArgsConstructor(access = AccessLevel.PRIVATE)

@Component
public class RestaurantTestData implements NamedBasedData<Restaurant> {

    public static final Restaurant RESTAURANT_UPDATED = new Restaurant(START_RESTAURANT_ID, "ресторан1_измененный");
    public static final Restaurant NEW_RESTAURANT = new Restaurant(TestDBData.NEXT_ID, "ресторан5");

    public static Restaurant newRestaurant() {
        return new Restaurant("ресторан5");
    }

    @Override
    public Restaurant first() {
        return RESTAURANT1;
    }

    @Override
    public List<Restaurant> all() {
        return Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Override
    public Restaurant create() {
        return new Restaurant("ресторан5");
    }

    @Override
    public Restaurant created() {
        return NEW_RESTAURANT;
    }

    @Override
    public List<Restaurant> allWithCreated() {
        return Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, NEW_RESTAURANT);
    }

    @Override
    public Restaurant changed() {
        return RESTAURANT_UPDATED;
    }

    @Override
    public List<Restaurant> allWithChanged() {
        return Arrays.asList(RESTAURANT_UPDATED, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Override
    public int deleteId() {
        return RESTAURANT1.getId();
    }

    @Override
    public List<Restaurant> allWithoutDeleted() {
        return Arrays.asList(RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Override
    public String firstPartOfName() {
        return "РеСтОрАн";
    }

    @Override
    public List<Restaurant> filtered() {
        return Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }
}
