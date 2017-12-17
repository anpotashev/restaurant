package ru.net.arh.service.price;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Price;
import ru.net.arh.repository.PriceRepository;
import ru.net.arh.service.PriceService;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.utils.aop.annotation.Throw404IfFalseResult;
import ru.net.arh.utils.aop.annotation.ThrowIfEmptyListReturned;
import ru.net.arh.utils.aop.annotation.ThrowIfException;
import ru.net.arh.utils.aop.annotation.ThrowIfLocalDateParamBeforeToday;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository repository;

//    @ThrowDuplicateValueForUniqueIndexIfNullResult
//    @CacheEvict("menu")
//    @ThrowIfLocalDateParamBeforeToday(message = "You cannot create price in past")
//    @Override
//    public Price create(LocalDate date, int restaurantId, int dishId, double price) {
//        log.debug("price.create({}, {}, {}, {})", date, restaurantId, dishId, price);
//        return repository.create(date, restaurantId, dishId, price);
//    }

    @ThrowIfException(message = "Error during creating new price(s)")
    @ThrowIfLocalDateParamBeforeToday(message = "You cannot create price in past")
    @ThrowIfEmptyListReturned
    @Override
    public List<Price> create(LocalDate date, int restaurantId, MenuItem... menuItems) {
        return repository.create(date, restaurantId, menuItems);
    }

    @ThrowIfException(message = "Error during editing price(s)")
    @ThrowIfLocalDateParamBeforeToday(message = "You cannot edit price in past")
    @Override
    public List<Price> update(LocalDate date, int restaurantId, MenuItem... menuItems) {
        return repository.update(date, restaurantId, menuItems);
    }

    @CacheEvict("menu")
    @Throw404IfFalseResult
    @ThrowIfLocalDateParamBeforeToday(message = "You cannot delete price in past")
    @Override
    public boolean delete(LocalDate date, int id) {
        return repository.delete(id);
    }


    @Override
    public List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date) {
        return repository.getAllForRestorantInDay(restaurantId, date);
    }

//    @Override
//    @Throw404IfNullResult
//    public Price update(LocalDate date, int id, int restaurantId, int dishId, double price) {
//        return repository.update(date, id, restaurantId, dishId, price);
//    }

}
