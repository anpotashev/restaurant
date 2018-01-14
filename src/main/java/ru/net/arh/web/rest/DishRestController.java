package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.model.Dish;
import ru.net.arh.web.controller.AbstractNamedController;
import ru.net.arh.web.controller.DishController;

@Slf4j
@RestController
@RequestMapping(RootRestController.ROOT_URL + "/dishes")
public class DishRestController extends AbstractNamedBasedRestController<Dish> {

    @Autowired
    private DishController dishController;

    @Override
    public AbstractNamedController<Dish> getController() {
        return dishController;
    }

    @Override
    public String getUri() {
        return RootRestController.ROOT_URL + "/dishes";
    }
}
