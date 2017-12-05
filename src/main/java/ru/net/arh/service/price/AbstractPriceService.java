package ru.net.arh.service.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import ru.net.arh.model.Price;
import ru.net.arh.repository.PriceRepository;
import ru.net.arh.to.menu.DayMenu;
import ru.net.arh.utils.MenuUtil;

import java.time.LocalDate;

import static ru.net.arh.utils.ValidationUnit.checkCreateResult;

abstract class AbstractPriceService implements PriceService {

    @Autowired
    private PriceRepository repository;

    @CacheEvict("menu")
    @Override
    public Price create(int restaurantId, int dishId, LocalDate date, double price) {
        return checkCreateResult(repository.create(restaurantId, dishId, date, price),
                "restaurantId=" + restaurantId
                        + ", dishId=" + dishId
                        + ", date=" + date
        );
    }

    @CacheEvict("menu")
    @Override
    public void delete(int restaurantId, int dishId, LocalDate date) {
        repository.delete(restaurantId, dishId, date);
    }

    @CacheEvict("menu")
    @Override
    public Price update(int restaurantId, int dishId, LocalDate date, double price) {
        return repository.update(restaurantId, dishId, date, price);
    }

    @Cacheable("menu")
    public DayMenu getDayMenu(LocalDate date) {
        return MenuUtil.convert(date, repository.getDayPricesWithFields(date));
    }


}
