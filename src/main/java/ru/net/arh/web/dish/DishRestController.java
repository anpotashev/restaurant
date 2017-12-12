package ru.net.arh.web.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.service.DishService;
import ru.net.arh.web.AbstractBaseController;

@Slf4j
@Controller
public class DishRestController extends AbstractBaseController<Dish> {
    @Autowired
    private DishService dishService;

    @Override
    protected AbstractBaseService<Dish> getService() {
        return dishService;
    }

}
