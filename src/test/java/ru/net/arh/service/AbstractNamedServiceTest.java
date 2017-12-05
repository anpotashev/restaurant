package ru.net.arh.service;

import org.junit.Assert;
import org.junit.Test;
import ru.net.arh.model.NamedBasedEntity;

public abstract class AbstractNamedServiceTest<T extends NamedBasedEntity> extends AbstractBaseServiceTest<T> {

    @Override
    protected abstract AbstractNamedService getService();

    @Test
    public void findAllByFirstPartOfNameIgnoringCase() throws Exception {
        Assert.assertEquals(0, getService().findAllByFirstPartOfNameIgnoringCase("abracatabra").size());
    }
}