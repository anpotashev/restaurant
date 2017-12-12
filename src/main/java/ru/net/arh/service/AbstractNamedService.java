package ru.net.arh.service;

import ru.net.arh.model.mapped.NamedBasedEntity;

import java.util.List;

public interface AbstractNamedService<T extends NamedBasedEntity> extends AbstractBaseService<T> {
    List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName);
}
