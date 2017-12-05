package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.utils.exception.CreateWithFoundKeyException;
import ru.net.arh.utils.exception.NotFoundException;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ValidationUnit {

    public static <T> T checkNotFound(T object, Serializable key) {
        return checkNotFound(object, "key=" + key);
    }

    public static void checkNotFoundWithKey(boolean found, Serializable key) {
        checkNotFound(found, "key=" + key);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found)
            throw new NotFoundException("Not found entity with " + msg);
    }

    public static <T> T checkCreateResult(T object, String keyDescription) {
        if (object != null) return object;
        throw new CreateWithFoundKeyException("Error creating entity. Already found with key: " + keyDescription);
    }
}
