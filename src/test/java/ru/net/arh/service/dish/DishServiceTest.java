package ru.net.arh.service.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractNamedBasedServiceTest;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.DishService;
import ru.net.arh.testdata.DishTestData;
import ru.net.arh.testdata.NamedBasedData;

@Slf4j
public class DishServiceTest extends AbstractNamedBasedServiceTest<Dish> {

    @Autowired
    private DishService service;

    @Autowired
    private DishTestData testData;

    @Override
    protected NamedBasedData<Dish> testData() {
        return testData;
    }

    @Override
    protected AbstractNamedService<Dish> service() {
        return service;
    }
}
