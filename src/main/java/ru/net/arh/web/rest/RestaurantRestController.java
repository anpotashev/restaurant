package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.model.Restaurant;
import ru.net.arh.web.controller.AbstractNamedController;

@Slf4j
@RestController
@RequestMapping(RootRestController.ROOT_URL + "/restaurants")
public class RestaurantRestController extends AbstractNamedBasedRestController<Restaurant> {


    @Autowired
    private AbstractNamedController restaurantController;

    @Override
    public AbstractNamedController<Restaurant> getController() {
        return restaurantController;
    }
}
