package ru.net.arh.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.model.Price;
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

    public void delete(int restaurantId, int id, LocalDate date) {
        priceService.delete(date, id, restaurantId);
    }

    public List<MenuItem> get(int restaurantId, LocalDate date) {
        return MenuUtil.convertToMenuItems(priceService.getAllForRestorantInDay(restaurantId, date));
    }

    public Price save(int restaurantId, LocalDate date, MenuItem menuItem) {
        return priceService.save(date, restaurantId, menuItem);
    }

    public MenuItem get(int priceId, int restaurantId, LocalDate date) {
        return MenuUtil.convertToMenuItem(priceService.get(priceId, restaurantId, date));
    }
}
