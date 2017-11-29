package ru.net.arh.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Dish;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.service.dish.DishService;
import ru.net.arh.web.AbstractBaseRestController;

import java.util.List;

@Slf4j
//@Controller
public class RestaurantRestController extends AbstractBaseRestController<Dish> {
    //    @Autowired
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
        return dishService.update(dish);
    }

    public void delete(int id) {
        dishService.delete(id);
    }


}
