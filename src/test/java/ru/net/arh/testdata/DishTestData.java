package ru.net.arh.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.net.arh.model.AbstractBaseEntity.START_SEQ;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DishTestData {


    public static final Dish DISH1 = new Dish(START_SEQ, "блюдо1");
    public static final Dish DISH2 = new Dish(START_SEQ + 1, "блюдо2");
    public static final Dish DISH3 = new Dish(START_SEQ + 2, "блюдо3");
    public static final Dish DISH4 = new Dish(START_SEQ + 3, "Блюдо4");
    public static final Dish DISH4_UPDATED = new Dish(START_SEQ + 3, "Блюдо4_измененное");
    public static final Dish NEW_DISH = new Dish(TestDBData.NEXT_ID, "блюдо5");

    public static Dish newDish() {
        return new Dish("блюдо5");
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
