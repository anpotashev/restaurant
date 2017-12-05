package ru.net.arh.model;

import java.io.Serializable;

public interface PrimaryKeyGettable<T extends Serializable> {

    //Just for user, dish and restaurant
    default boolean isNew() {
        return getKey() != null;
    }

    T getKey();
}
