package ru.net.arh.service.impl.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.Restaurant;
import ru.net.arh.service.AbstractNamedBasedServiceTest;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.RestaurantService;
import ru.net.arh.testdata.NamedBasedData;
import ru.net.arh.testdata.RestaurantTestData;

@Slf4j
public class RestaurantServiceTest extends AbstractNamedBasedServiceTest<Restaurant> {

    @Autowired
    private RestaurantTestData testData;
    @Autowired
    private RestaurantService service;

    @Override
    protected NamedBasedData<Restaurant> testData() {
        return testData;
    }

    @Override
    protected AbstractNamedService<Restaurant> service() {
        return service;
    }
}
