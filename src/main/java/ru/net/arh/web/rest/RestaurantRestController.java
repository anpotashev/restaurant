package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.model.Restaurant;
import ru.net.arh.service.AbstractNamedService;
import ru.net.arh.service.RestaurantService;

@Slf4j
@RestController
@RequestMapping(RootRest.ROOT_URL + "/restaurants")
public class RestaurantRestController extends AbstractNamedBasedRestController<Restaurant> {


    @Autowired
    private RestaurantService restaurantService;

    @Override
    protected AbstractNamedService<Restaurant> gerService() {
        return restaurantService;
    }

    @Override
    public String getUri() {
        return RootRest.ROOT_URL + "/restaurants";
    }
}
