package ru.net.arh.to.menu;

import lombok.Getter;
import lombok.ToString;
import ru.net.arh.model.Dish;
import ru.net.arh.model.Price;

import java.math.BigDecimal;

@Getter
@ToString
public class RestaurantMenuItem {

    private Dish dish;
    private BigDecimal price;

    public RestaurantMenuItem(Price price) {
        this.dish = price.getKey().getDish();
        this.price = price.getPrice();
    }
}
