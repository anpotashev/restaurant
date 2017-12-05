package ru.net.arh.service;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.NamedBasedEntity;
import ru.net.arh.repository.AbstractNamedBasedRepository;

import java.util.List;

@Slf4j
public abstract class AbstractNamedServiceImpl<T extends NamedBasedEntity> extends AbstractBaseServiceImpl<T> implements AbstractNamedService<T> {

    protected abstract AbstractNamedBasedRepository<T> getRepository();

    @Override
    public List<T> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName) {
        return getRepository().findAllByFirstPartOfNameIgnoringCase(firstPartOfName);
    }

}
