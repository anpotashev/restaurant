package ru.net.arh.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.testdata.NamedBasedData;
import ru.net.arh.utils.validation.exception.ValidationException;

import java.util.List;

import static ru.net.arh.testdata.GenericTestClass.assertMatch;

@Slf4j
public abstract class AbstractNamedBasedServiceTest<T extends NamedBasedEntity> extends AbstractServiceTest {

    protected abstract NamedBasedData<T> testData();

    protected abstract AbstractNamedService<T> service();

    @Test
    public void get() throws Exception {
        T object = service().get(testData().first().getId());
        assertMatch(object, testData().first());
    }

    @Test
    public void getWithWrongId() throws Exception {
        thrown.expect(ValidationException.class);
        service().get(-1);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void create() throws Exception {
        service().save(testData().create());
        List actual = service().getAll();
        assertMatch(actual, testData().allWithCreated());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createWithEmptyName() throws Exception {
        T object = testData().create();
        object.setName("");
        thrown.expect(ValidationException.class);
        service().save(object);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void update() throws Exception {
        T object = testData().changed();
        service().save(object);
        List actual = service().getAll();
        assertMatch(actual, testData().allWithChanged());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongId() throws Exception {
        T object = testData().create();
        object.setId(-1);
        thrown.expect(ValidationException.class);
        service().save(object);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithEmptyId() throws Exception {
        T object = testData().create();
        object.setId(-1);
        thrown.expect(ValidationException.class);
        service().save(object);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithEmptyName() throws Exception {
        T object = service().get(testData().first().getId());
        object.setName("");
        thrown.expect(ValidationException.class);
        service().save(object);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void delete() throws Exception {
        service().delete(testData().deleteId());
        List actual = service().getAll();
        assertMatch(actual, testData().allWithoutDeleted());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithWrongId() throws Exception {
        thrown.expect(ValidationException.class);
        service().delete(-1);
    }

    @Test
    public void getAll() throws Exception {
        List actual = service().getAll();
        assertMatch(actual, testData().all());
    }

    @Test
    public void findStaringWithNameIgnoreCase() throws Exception {
        List actual = service().findAllByFirstPartOfNameIgnoringCase(testData().firstPartOfName());
        assertMatch(actual, testData().filtered());
    }

}
