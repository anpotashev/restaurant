package ru.net.arh.repository;

import ru.net.arh.model.mapped.AbstractBaseEntity;

import java.util.List;

public interface AbstractBasedRepository<T extends AbstractBaseEntity> {

    T find(final int id);

    T save(T value);

//    default T save(final T value) {
//        if (!value.isNew() && find(value.getId()) == null) {
//            return null;
//        }
//        return createOrUpdate(value);
//    }

//    ;

//    T findOne(int id);

//    T createOrUpdate(T value);

    boolean delete(final int id);

    List<T> findAll();
}