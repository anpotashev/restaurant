package ru.net.arh.repository;

import ru.net.arh.model.NamedBasedEntity;

import java.util.List;

public interface AbstractNamedBasedRepository<T extends NamedBasedEntity> extends AbstractBasedRepository<T, Integer> {
    List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName);
}
