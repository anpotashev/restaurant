package ru.net.arh.testdata;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GenericTestClass<T> {

    public static void assertMatch(Object actual, Object expected) {
        assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Object> actual, Object... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

//    public static void assertMatch(Iterable<Object> actual, Iterable<Object> expected) {
//        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
//    }

    public static void assertMatch(Iterable actual, Iterable expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
