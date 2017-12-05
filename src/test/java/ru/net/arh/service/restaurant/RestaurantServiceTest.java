package ru.net.arh.service.restaurant;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.Restaurant;
import ru.net.arh.service.AbstractNamedServiceTest;
import ru.net.arh.testdata.RestaurantTestData;
import ru.net.arh.utils.exception.NotFoundException;

import java.util.List;

import static ru.net.arh.model.AbstractBaseEntity.START_SEQ;
import static ru.net.arh.testdata.GenericTestClass.assertMatch;
import static ru.net.arh.testdata.RestaurantTestData.NEW_RESTAURANT;
import static ru.net.arh.testdata.RestaurantTestData.RESTAURANT_UPDATED;
import static ru.net.arh.testdata.TestDBData.*;

public class RestaurantServiceTest extends AbstractNamedServiceTest<Restaurant> {

    @Autowired
    private RestaurantService service;

    @Override
    protected RestaurantService getService() {
        return service;
    }
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = getService().get(START_RESTAURANT_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void create() throws Exception {
        getService().create(RestaurantTestData.newRestaurant());
        List actual = getService().getAll();
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, NEW_RESTAURANT);
    }

    @Test
    public void createWithEmptyName() throws Exception {
        Restaurant restaurant = RestaurantTestData.newRestaurant();
        restaurant.setName("");
        getService().create(restaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant restaurant = new Restaurant(RESTAURANT_UPDATED.getKey(), RESTAURANT_UPDATED.getName());
        getService().update(restaurant);
        List actual = getService().getAll();
        assertMatch(actual, RESTAURANT_UPDATED, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Test
    public void updateWithWrongId() throws Exception {
        Restaurant restaurant = new Restaurant(-1, "restaurant with wrong id");
        thrown.expect(NotFoundException.class);
        getService().update(restaurant);
    }

    @Test
    public void updateWithEmptyName() throws Exception {
        Restaurant restaurant = getService().get(START_SEQ);
        restaurant.setName("");
        getService().update(restaurant);
        throw new RuntimeException("not working validation yet");
    }

    @Test
    public void delete() throws Exception {
        getService().delete(START_RESTAURANT_ID);
        List actual = getService().getAll();
        assertMatch(actual, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        thrown.expect(NotFoundException.class);
        getService().delete(-1);
    }

    @Test
    public void getAll() throws Exception {
        List actual = getService().getAll();
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Test
    public void findStaringWithNameIgnoreCase() throws Exception {
        List actual = getService().findAllByFirstPartOfNameIgnoringCase("ресторан");
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }


}