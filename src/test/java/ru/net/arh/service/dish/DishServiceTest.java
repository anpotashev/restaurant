package ru.net.arh.service.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractNamedServiceTest;
import ru.net.arh.service.DishService;
import ru.net.arh.testdata.DishTestData;
import ru.net.arh.testdata.GenericTestClass;
import ru.net.arh.utils.exception.Exception404;

import java.util.List;

import static ru.net.arh.model.mapped.AbstractBaseEntity.START_SEQ;
import static ru.net.arh.testdata.DishTestData.DISH4_UPDATED;
import static ru.net.arh.testdata.DishTestData.NEW_DISH;
import static ru.net.arh.testdata.GenericTestClass.assertMatch;
import static ru.net.arh.testdata.TestDBData.*;

public class DishServiceTest extends AbstractNamedServiceTest<Dish> {

    @Autowired
    private DishService service;

    @Override
    protected DishService getService() {
        return service;
    }


    @Test
    public void get() throws Exception {
        Dish dish = getService().get(START_SEQ);
        assertMatch(dish, DISH1);
    }

    @Test
    public void update() throws Exception {
        Dish dish = new Dish(DISH4_UPDATED.getId(), DISH4_UPDATED.getName());
        getService().update(dish);
        List actual = getService().getAll();
        assertMatch(actual, DISH1, DISH2, DISH3, DISH4_UPDATED);
    }

    @Test
    public void updateWithWrongId() throws Exception {
        Dish dish = new Dish(-1, "dish with wrong id");
        thrown.expect(Exception404.class);
        getService().update(dish);
    }

    @Test
    public void updateWithEmptyName() throws Exception {
        Dish dish = service.get(DISH1.getId());
        dish.setName("");
        service.update(dish);
        throw new RuntimeException("not working validation yet");
    }

    @Test
    public void create() throws Exception {
        getService().create(DishTestData.newDish());
        List actual = getService().getAll();
        GenericTestClass.assertMatch(actual, DISH1, DISH2, DISH3, DISH4, NEW_DISH);
    }

    @Test
    public void createWithEmptyName() throws Exception {
        Dish dish = DishTestData.newDish();
        dish.setName("");
        service.create(dish);
    }

    @Test
    public void getAll() throws Exception {
        List actual = getService().getAll();
        assertMatch(actual, DISH1, DISH2, DISH3, DISH4);
    }


    @Test
    public void delete() throws Exception {
        service.delete(START_SEQ);
        List actual = service.getAll();
        assertMatch(actual, DISH2, DISH3, DISH4);
    }


    @Test
    public void findStaringWithNameIgnoreCase() throws Exception {
        List actual = getService().findAllByFirstPartOfNameIgnoringCase("блюдо");
        assertMatch(actual, DISH1, DISH2, DISH3, DISH4);
    }


}