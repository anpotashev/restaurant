package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.model.Dish;
import ru.net.arh.web.AbstractNamedController;
import ru.net.arh.web.dish.DishController;

@Slf4j
@RestController
@RequestMapping("/dishes")
public class DishRestController extends AbstractNamedBasedRestController<Dish> {

    @Autowired
    private DishController dishController;

    @Override
    public AbstractNamedController<Dish> getController() {
        return dishController;
    }
}
