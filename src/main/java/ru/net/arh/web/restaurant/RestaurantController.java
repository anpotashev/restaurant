package ru.net.arh.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.model.Restaurant;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.RestaurantService;
import ru.net.arh.web.AbstractNamedController;

@Slf4j
@Controller
public class RestaurantController extends AbstractNamedController<Restaurant> {
    @Autowired
    private RestaurantService restaurantService;

    @Override
    protected AbstractNamedService<Restaurant> getService() {
        return restaurantService;
    }
}
