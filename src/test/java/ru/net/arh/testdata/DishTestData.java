package ru.net.arh.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Dish;

import static ru.net.arh.model.mapped.AbstractBaseEntity.START_SEQ;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DishTestData {

    public static final Dish DISH4_UPDATED = new Dish(START_SEQ + 3, "Блюдо4_измененное");
    public static final Dish NEW_DISH = new Dish(TestDBData.NEXT_ID, "блюдо5");

    public static Dish newDish() {
        return new Dish("блюдо5");
    }

}