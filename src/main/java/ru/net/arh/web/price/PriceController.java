package ru.net.arh.web.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.service.PriceService;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.utils.MenuUtil;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
public class PriceController {

    @Autowired
    private PriceService priceService;

    public void delete(int id, LocalDate date) {
        priceService.delete(date, id);
    }

    public List<MenuItem> get(int restaurantId, LocalDate date) {
        return MenuUtil.convertToMenuItems(priceService.getAllForRestorantInDay(restaurantId, date));
    }

    public List<MenuItem> create(int restaurantId, LocalDate date, MenuItem... menuItems) {
        return MenuUtil.convertToMenuItems(priceService.create(date, restaurantId, menuItems));
    }

    public List<MenuItem> update(int restaurantId, LocalDate date, MenuItem... menuItems) {
        return MenuUtil.convertToMenuItems(priceService.update(date, restaurantId, menuItems));
    }

}
