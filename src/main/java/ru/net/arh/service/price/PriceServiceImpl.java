package ru.net.arh.service.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Price;
import ru.net.arh.repository.PriceRepository;
import ru.net.arh.service.PriceService;
import ru.net.arh.utils.aop.annotation.NeedValidateReturnValueForNullOnCreate;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {
//    @Autowired
//    private PriceServiceImpl thisService;

    @Autowired
    private PriceRepository repository;

    @NeedValidateReturnValueForNullOnCreate
    @CacheEvict("menu")
    @Override
    public Price create(int restaurantId, int dishId, LocalDate date, double price) {
        return repository.create(restaurantId, dishId, date, price);
    }

    @CacheEvict("menu")
    @Override
    public void delete(int restaurantId, int dishId, LocalDate date) {
        repository.delete(restaurantId, dishId, date);
    }


    @NeedValidateReturnValueForNullOnCreate
    @CacheEvict("menu")
    @Override
    public Price update(int restaurantId, int dishId, LocalDate date, double price) {
        return repository.update(restaurantId, dishId, date, price);
    }

    @Override
    public List<Price> getAllForRestorantInDay(int restaurantId, LocalDate date) {
        return repository.getAllForRestorantInDay(restaurantId, date);
    }


}
