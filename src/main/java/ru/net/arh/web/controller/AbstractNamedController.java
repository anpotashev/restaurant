package ru.net.arh.web.controller;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.service.AbstractNamedService;

import java.util.List;

@Slf4j
public abstract class AbstractNamedController<T extends NamedBasedEntity> extends AbstractBaseController<T> {

    @Override
    protected abstract AbstractNamedService<T> getService();

    public List<T> getByFirstPartOfName(String firstPartOfName) {
        return getService().findAllByFirstPartOfNameIgnoringCase(firstPartOfName);
    }
}
