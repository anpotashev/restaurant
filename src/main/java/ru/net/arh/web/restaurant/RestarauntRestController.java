package ru.net.arh.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.AuthorizedUser;
import ru.net.arh.model.Price;
import ru.net.arh.model.Restaurant;
import ru.net.arh.service.AbstractBaseService;
import ru.net.arh.service.restaurant.RestaurantService;
import ru.net.arh.web.AbstractBaseRestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class RestarauntRestController extends AbstractBaseRestController<Restaurant> {

    @Autowired
    private RestaurantService restaurantService;
    @Override
    protected AbstractBaseService<Restaurant> getBaseService() {
        return restaurantService;
    }


    public boolean voteRestaurant(int restaurant_id) {
        return restaurantService.voteRestaurant(restaurant_id, AuthorizedUser.id(), LocalDateTime.now());
    }


}
