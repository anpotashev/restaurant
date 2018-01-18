package ru.net.arh.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.net.arh.model.mapped.NamedBasedEntity;

import java.util.List;

public interface AbstractNamedService<T extends NamedBasedEntity> extends AbstractBaseService<T> {
    T get(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    T save(T value);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    boolean delete(int id);

    List<T> getAll();

    List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName);
}
