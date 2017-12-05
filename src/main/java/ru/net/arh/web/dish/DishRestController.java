package ru.net.arh.web.dish;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.service.dish.DishServiceImpl;
import ru.net.arh.web.AbstractBaseRestController;

import java.util.List;

@Slf4j
//@Controller
public class DishRestController extends AbstractBaseRestController<Dish> {
    //    @Autowired
    private DishServiceImpl dishService;

    @Override
    protected AbstractBaseService<Dish> getBaseService() {
        return dishService;
    }

    public Dish get(int key) {
        return dishService.get(key);
    }

    public List<Dish> getAll() {
        return dishService.getAll();
    }


    public Dish save(Dish dish) {
        return dishService.update(dish);
    }

    public void delete(int key) {
        dishService.delete(key);
    }


}
