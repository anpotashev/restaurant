package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.Restaurant;
import ru.net.arh.testdata.NamedBasedData;
import ru.net.arh.testdata.RestaurantTestData;

@Slf4j
public class RestaurantRestControllerTest extends AbstractNamedRestControllerTest<Restaurant> {
    private static final String URI = BASE_REST_URI + "restaurants";

    @Autowired
    private RestaurantTestData testData;

    @Override
    public String getUri() {
        return URI;
    }

    @Override
    protected NamedBasedData<Restaurant> getTestData() {
        return testData;
    }
}
