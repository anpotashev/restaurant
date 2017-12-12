package ru.net.arh.repository;

import ru.net.arh.model.mapped.NamedBasedEntity;

import java.util.List;

public interface AbstractNamedBasedRepository<T extends NamedBasedEntity> extends AbstractBasedRepository<T> {

    List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName);

}
