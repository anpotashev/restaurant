package ru.net.arh.web.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.service.price.PriceService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
//@Controller
public class PriceRestController {

    @Autowired
    private PriceService priceService;

    public void addPrice(int restaurant_id, int dish_id, BigDecimal price) {
        editPrice(restaurant_id, dish_id, LocalDate.now(), price);
    }

    public void editPrice(int restaurant_id, int dish_id, LocalDate date, BigDecimal price) {
//        priceService.save(restaurant_id, dish_id, date, price);
    }

    public void deletePrice(int restaurant_id, int dish_id, LocalDate date) {
        priceService.delete(restaurant_id, dish_id, date);
    }

//    public List<Price> getOnTheDay(int restaurant_id, LocalDate date) {
//        return priceService.getDayPrices(restaurant_id, date);
//    }
//
//    public List<Price> getOnToday(int restaurant_id) {
//        return priceService.getDayPrices(restaurant_id, LocalDate.now());
//    }



}
