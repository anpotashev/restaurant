package ru.net.arh.repository.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;
import ru.net.arh.model.MenuItem;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.MenuItemRepository;
import ru.net.arh.to.menu.MenuItemTo;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
@Transactional(readOnly = true)
@PropertySource("classpath:db/db.properties")
public class MenuItemRepositoryImpl implements MenuItemRepository {

    @Autowired
    private DataJpaMenuItemRepository priceRepository;
    @Autowired
    private DataJpaRestaurantRepository restaurantRepository;
    @Autowired
    private DataJpaDishRepository dishRepository;

    @Override
    @Transactional
    public MenuItem save(LocalDate date, int restaurantId, MenuItemTo menuItemTo) {
        if (menuItemTo.getId() != null && get(menuItemTo.getId(), restaurantId, date) == null) {
            return null;
        }
        MenuItem menuItem = getPrice(date, restaurantId, menuItemTo);
        return priceRepository.save(menuItem);
    }

    @Override
    public MenuItem get(int id, int restaurantId, LocalDate date) {
        return priceRepository.getByIdAndRestaurantIdAndDate(id, restaurantId, date).orElse(null);
    }

    private MenuItem getPrice(LocalDate date, int restaurantId, MenuItemTo menuItemTo) {
        return getPrice(menuItemTo.getId(), restaurantId, menuItemTo.getDishId(), date, menuItemTo.getPrice());
    }

    @Override
    @Transactional
    public boolean delete(LocalDate date, int id, int restaurantId) {
        return priceRepository.deleteByIdAndDateAndRestaurantId(id, date, restaurantId) > 0;
    }


    @Override
    public List<MenuItem> getAllForRestorantInDay(int restaurantId, LocalDate date) {
        return priceRepository.getAllByRestaurantIdAndDateOrderByDishId(restaurantId, date);
    }

    private MenuItem getPrice(Integer id, int restaurantId, int dishId, LocalDate date, double price) {
        MenuItem result = getPrice(restaurantId, dishId, date, price);
        result.setId(id);
        return result;
    }

    private MenuItem getPrice(int restaurantId, int dishId, LocalDate date, double price) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        Dish dish = dishRepository.getOne(dishId);
        return new MenuItem(restaurant, dish, date, price);
    }

}
