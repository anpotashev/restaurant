package ru.net.arh.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.DishService;

@Slf4j
@Controller
public class DishController extends AbstractNamedController<Dish> {
    @Autowired
    private DishService dishService;

    @Override
    protected AbstractNamedService<Dish> getService() {
        return dishService;
    }

}
