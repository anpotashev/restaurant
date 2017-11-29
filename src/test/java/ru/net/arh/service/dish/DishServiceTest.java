package ru.net.arh.service.dish;

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
import ru.net.arh.model.Dish;
import ru.net.arh.testdata.DishTestData;
import ru.net.arh.utils.exception.NotFoundException;

import java.util.List;

import static ru.net.arh.model.AbstractBaseEntity.START_SEQ;
import static ru.net.arh.testdata.DishTestData.*;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql")
public class DishServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private DishService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(START_SEQ);
        DishTestData.assertMatch(dish, DISH1);
    }

    @Test
    public void getWithWrongId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.get(-1);
    }

    @Test
    public void create() throws Exception {
        service.create(DishTestData.newDish());
        List<Dish> dishes = service.getAll();
        assertMatch(dishes, DISH1, DISH2, DISH3, DISH4, NEW_DISH);
    }

    @Test
    public void createWithEmptyName() throws Exception {
        Dish dish = DishTestData.newDish();
        dish.setName("");
        service.create(dish);
    }

    @Test
    public void update() throws Exception {
        Dish dish = new Dish(DISH4_UPDATED.getId(), DISH4_UPDATED.getName());
        service.update(dish);
        List<Dish> dishes = service.getAll();
        assertMatch(dishes, DISH1, DISH2, DISH3, DISH4_UPDATED);
//        throw new RuntimeException("not implemented yet");
    }

    @Test
    public void updateWithWrongId() throws Exception {
        Dish dish = new Dish(-1, "dish with wrong id");
        thrown.expect(NotFoundException.class);
        service.update(dish);
    }

    @Test
    public void updateWithEmptyName() throws Exception {
        Dish dish = service.get(START_SEQ);
        dish.setName("");
        service.update(dish);
//        throw new RuntimeException("not working validation yet");
    }

    @Test
    public void delete() throws Exception {
        service.delete(START_SEQ);
        List<Dish> dishes = service.getAll();
        assertMatch(dishes, DISH2, DISH3, DISH4);
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(-1);
    }

    @Test
    public void getAll() throws Exception {
        List<Dish> dishes = service.getAll();
        assertMatch(dishes, DISH1, DISH2, DISH3, DISH4);
    }

    @Test
    public void findStaringWithName() throws Exception {
        List<Dish> dishes = service.findStaringWithName("блюдо");
        assertMatch(dishes, DISH1, DISH2, DISH3);
    }

    @Test
    public void findStaringWithNameIgnoreCase() throws Exception {
        List<Dish> dishes = service.findStaringWithNameIgnoreCase("блюдо");
        assertMatch(dishes, DISH1, DISH2, DISH3, DISH4);
    }
}