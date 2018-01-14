package ru.net.arh.testdata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.net.arh.model.Dish;

import java.util.Arrays;
import java.util.List;

import static ru.net.arh.model.mapped.AbstractBaseEntity.START_SEQ;
import static ru.net.arh.testdata.TestDBData.*;

@Slf4j
@Component
public class DishTestData implements NamedBasedData<Dish> {

    public static final Dish DISH4_UPDATED = new Dish(START_SEQ + 3, "Блюдо4_измененное");
    public static final Dish NEW_DISH = new Dish(TestDBData.NEXT_ID, "блюдо5");

    public static Dish newDish() {
        return new Dish("блюдо5");
    }

    @Override
    public Dish first() {
        return DISH1;
    }

    @Override
    public List<Dish> all() {
        return Arrays.asList(DISH1, DISH2, DISH3, DISH4);
    }

    @Override
    public Dish create() {
        return new Dish("блюдо5");
    }

    @Override
    public Dish created() {
        return NEW_DISH;
    }

    @Override
    public List<Dish> allWithCreated() {
        return Arrays.asList(DISH1, DISH2, DISH3, DISH4, NEW_DISH);
    }

    @Override
    public Dish changed() {
        return DISH4_UPDATED;
    }

    @Override
    public List<Dish> allWithChanged() {
        return Arrays.asList(DISH1, DISH2, DISH3, DISH4_UPDATED);
    }

    @Override
    public int deleteId() {
        return DISH1.getId();
    }

    @Override
    public List<Dish> allWithoutDeleted() {
        return Arrays.asList(DISH2, DISH3, DISH4);
    }

    @Override
    public String firstPartOfName() {
        return "БлЮдО";
    }

    @Override
    public List<Dish> filtered() {
        return Arrays.asList(DISH1, DISH2, DISH3);
    }
}