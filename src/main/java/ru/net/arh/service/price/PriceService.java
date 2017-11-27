package ru.net.arh.service.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Price;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PriceService {

    public Price save(int restaurant_id, int dish_id, LocalDate date, BigDecimal price) {
        return null;
    }

    public boolean delete(int restaurant_id, int dish_id, LocalDate date) {
        return false;
    }

    public List<Price> getPricesOnTheDay(int restaurant_id, LocalDate date) {
        return null;
    }
}
