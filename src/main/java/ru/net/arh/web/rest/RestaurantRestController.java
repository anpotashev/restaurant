package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.net.arh.model.Restaurant;
import ru.net.arh.web.restaurant.RestaurantController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

    @Autowired
    private RestaurantController restaurantController;

    @GetMapping
    public List<Restaurant> get() {
        return restaurantController.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantController.get(id);
    }

    @GetMapping("/search/{name}")
    public List<Restaurant> get(@PathVariable("name") String name) {
        return restaurantController.getByFirstPartOfName(name);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        restaurantController.delete(id);
    }

    @PostMapping
    @PutMapping
    public void save(@RequestBody Restaurant restaurant) {
        restaurantController.save(restaurant);
    }

}
