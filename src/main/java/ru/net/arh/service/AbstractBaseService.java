package ru.net.arh.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.net.arh.model.mapped.AbstractBaseEntity;

import java.util.List;

public interface AbstractBaseService<T extends AbstractBaseEntity> {

    T get(int id);


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    T save(T value);
//
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    T create(T value);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean delete(int id);

    List<T> getAll();
}
