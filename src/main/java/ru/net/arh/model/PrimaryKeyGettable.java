package ru.net.arh.model;

import java.io.Serializable;

public interface PrimaryKeyGettable<T extends Serializable> {

    default boolean isNew() {
        return getKey() != null;
    }

    T getKey();
}
