package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.utils.exception.NotFoundException;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ValidationUnit {

    public static <T> T checkNotFound(T object, Serializable id) {
        return checkNotFound(object, "key=" + id);
    }

    public static <T> T checkNotFound(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found)
            throw new NotFoundException("Not found entity with " + msg);
    }

}
