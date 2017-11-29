package ru.net.arh.service.restaurant;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.net.arh.configuration.SpringConfig;
import ru.net.arh.model.Restaurant;
import ru.net.arh.testdata.RestaurantTestData;
import ru.net.arh.utils.exception.NotFoundException;

import java.util.List;

import static ru.net.arh.model.AbstractBaseEntity.START_SEQ;
import static ru.net.arh.testdata.GenericTestClass.assertMatch;
import static ru.net.arh.testdata.RestaurantTestData.NEW_RESTAURANT;
import static ru.net.arh.testdata.RestaurantTestData.RESTAURANT_UPDATED;
import static ru.net.arh.testdata.TestDBData.*;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql")
public class RestaurantServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RestaurantService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(START_RESTAURANT_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getWithWrongId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(-1);
    }

    @Test
    public void create() throws Exception {
        service.create(RestaurantTestData.newRestaurant());
        List actual = service.getAll();
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, NEW_RESTAURANT);
    }

    @Test
    public void createWithEmptyName() throws Exception {
        Restaurant restaurant = RestaurantTestData.newRestaurant();
        restaurant.setName("");
        service.create(restaurant);
    }

    @Test
    public void update() throws Exception {
        Restaurant restaurant = new Restaurant(RESTAURANT_UPDATED.getId(), RESTAURANT_UPDATED.getName());
        service.update(restaurant);
        List actual = service.getAll();
        assertMatch(actual, RESTAURANT_UPDATED, RESTAURANT2, RESTAURANT3, RESTAURANT4);
//        throw new RuntimeException("not implemented yet");
    }

    @Test
    public void updateWithWrongId() throws Exception {
        Restaurant restaurant = new Restaurant(-1, "restaurant with wrong id");
        thrown.expect(NotFoundException.class);
        service.update(restaurant);
    }

    @Test
    public void updateWithEmptyName() throws Exception {
        Restaurant restaurant = service.get(START_SEQ);
        restaurant.setName("");
        service.update(restaurant);
        throw new RuntimeException("not working validation yet");
    }

    @Test
    public void delete() throws Exception {
        service.delete(START_RESTAURANT_ID);
        List actual = service.getAll();
        assertMatch(actual, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(-1);
    }

    @Test
    public void getAll() throws Exception {
        List actual = service.getAll();
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }

    @Test
    public void findStaringWithName() throws Exception {
        List actual = service.findStaringWithName("Ресторан");
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3);
    }

    @Test
    public void findStaringWithNameIgnoreCase() throws Exception {
        List actual = service.findStaringWithNameIgnoreCase("ресторан");
        assertMatch(actual, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
    }
}