package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.DishService;

@Slf4j
@RestController
@RequestMapping(RootRest.ROOT_URL + "/dishes")
public class DishRestController extends AbstractNamedBasedRestController<Dish> {

    @Autowired
    private DishService dishService;

    @Override
    protected AbstractNamedService<Dish> gerService() {
        return dishService;
    }

    @Override
    public String getUri() {
        return RootRest.ROOT_URL + "/dishes";
    }
}
