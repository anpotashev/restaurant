package ru.net.arh.service;

import org.junit.Before;
import org.junit.Test;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.utils.exception.Exception404;

public abstract class AbstractBaseServiceTest<T extends AbstractBaseEntity> extends BaseTest {
    @Before
    public void setUp() throws Exception {
    }

    protected abstract AbstractBaseService getService();

    @Test
    public void getWithEmptyResult() throws Exception {
        thrown.expect(Exception404.class);
        getService().get(-1);
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        thrown.expect(Exception404.class);
        getService().delete(-1);
    }
}