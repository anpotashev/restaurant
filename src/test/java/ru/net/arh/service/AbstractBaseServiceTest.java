package ru.net.arh.service;

import org.junit.Before;
import org.junit.Test;
import ru.net.arh.model.mapped.AbstractBaseEntity;
import ru.net.arh.utils.exception.NotFoundException;

public abstract class AbstractBaseServiceTest<T extends AbstractBaseEntity> extends BaseTest {
    @Before
    public void setUp() throws Exception {
    }

    protected abstract AbstractBaseService getService();

    @Test
    public void getWithEmptyResult() throws Exception {
        thrown.expect(NotFoundException.class);
        getService().get(-1);
    }

    @Test
    public void deleteWithWrongId() throws Exception {
        thrown.expect(NotFoundException.class);
        getService().delete(-1);
    }
}