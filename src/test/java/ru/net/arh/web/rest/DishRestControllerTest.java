package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.Dish;
import ru.net.arh.testdata.DishTestData;
import ru.net.arh.testdata.NamedBasedData;

@Slf4j
public class DishRestControllerTest extends AbstractNamedRestControllerTest<Dish> {
    private static final String URI = BASE_REST_URI + "dishes";

    @Autowired
    private DishTestData testData;

    @Override
    protected NamedBasedData<Dish> getTestData() {
        return testData;
    }

    @Override
    protected String getUri() {
        return URI;
    }
}
