package ru.net.arh.repository;

import ru.net.arh.model.PrimaryKeyGettable;

import java.io.Serializable;
import java.util.List;

public interface AbstractBasedRepository<T extends PrimaryKeyGettable<K>, K extends Serializable> {

    public T find(final K key);

    public T create(final T value);

    public T update(final T value);

    public boolean delete(final K key);

    public List<T> findAll();


}
