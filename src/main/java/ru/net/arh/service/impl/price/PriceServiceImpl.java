package ru.net.arh.service.impl.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Price;
import ru.net.arh.repository.PriceRepository;
import ru.net.arh.service.PriceService;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.utils.validation.annotation.CheckForException;
import ru.net.arh.utils.validation.annotation.CheckForFalseResult;
import ru.net.arh.utils.validation.annotation.CheckForLocalDateParamBeforeToday;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository repository;

    @CheckForException(message = "Error during creating or changing price", status = HttpStatus.NOT_MODIFIED)
    @CheckForLocalDateParamBeforeToday(message = "You cannot create/change price in the past", status = HttpStatus.NOT_ACCEPTABLE)
    @CheckForNullResult(status = HttpStatus.NOT_MODIFIED)
    @Override
    public Price save(LocalDate date, int restaurantId, MenuItem menuItem) {
        return repository.save(date, restaurantId, menuItem);
    }

    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @Override
    public Price get(int priceId, int restaurantId, LocalDate date) {
        return repository.get(priceId, restaurantId, date);
    }

    @CacheEvict(value = "menu", key = "#date")
    @CheckForFalseResult(status = HttpStatus.NOT_FOUND)
    @CheckForLocalDateParamBeforeToday(message = "You cannot delete price in the past", status = HttpStatus.NOT_ACCEPTABLE)
    @Override
    public boolean delete(LocalDate date, int id, int restaurantId) {
        return repository.delete(date, id, restaurantId);
    }

    @Cacheable("menu")
    @Override
    public List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date) {
        return repository.getAllForRestorantInDay(restaurantId, date);
    }

}
