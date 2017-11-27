package ru.net.arh.web.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.service.dish.DishService;
import ru.net.arh.web.AbstractBaseRestController;

import java.util.List;

@Slf4j
@Controller
public class DishRestController extends AbstractBaseRestController<Dish> {
    @Autowired
    private DishService dishService;

    @Override
    protected AbstractBaseService<Dish> getBaseService() {
        return dishService;
    }

    public Dish get(int id) {
        return dishService.get(id);
    }

    public List<Dish> getAll() {
        return dishService.getAll();
    }


    public Dish save(Dish dish) {
        return dishService.save(dish);
    }

    public void delete(Dish dish) {
        dishService.delete(dish);
    }


}
