package ru.net.arh.service.impl.menu_item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.net.arh.model.MenuItem;
import ru.net.arh.repository.MenuItemRepository;
import ru.net.arh.service.MenuItemService;
import ru.net.arh.to.menu.MenuItemTo;
import ru.net.arh.utils.MenuUtil;
import ru.net.arh.utils.validation.annotation.CheckForException;
import ru.net.arh.utils.validation.annotation.CheckForFalseResult;
import ru.net.arh.utils.validation.annotation.CheckForLocalDateParamBeforeToday;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    private MenuItemRepository repository;

    @Caching(evict = {
            @CacheEvict(value = "menu", key = "{#date, #restaurantId}")
            , @CacheEvict(value = "menu", key = "{#date, #restaurantId, #menuItemTo.id}")
    })
    @CheckForException(message = "Error during creating or changing price", status = HttpStatus.NOT_MODIFIED)
    @CheckForLocalDateParamBeforeToday(message = "You cannot create/change price in the past", status = HttpStatus.NOT_ACCEPTABLE)
    @CheckForNullResult(status = HttpStatus.NOT_MODIFIED)
    @Override
    public MenuItem save(LocalDate date, int restaurantId, MenuItemTo menuItemTo) {
        return repository.save(date, restaurantId, menuItemTo);
    }

    @Cacheable(value = "menu", key = "{#date, #restaurantId, #priceId}")
    @CheckForNullResult(status = HttpStatus.NOT_FOUND)
    @Override
    public MenuItemTo get(LocalDate date, int restaurantId, int priceId) {
        return MenuUtil.convertToMenuItemTo(repository.get(priceId, restaurantId, date));
    }

    @Caching(evict = {
            @CacheEvict(value = "menu", key = "{#date, #restaurantId}")
            , @CacheEvict(value = "menu", key = "{#date, #restaurantId, #id}")
    })
    @CheckForFalseResult(status = HttpStatus.NOT_FOUND)
    @CheckForLocalDateParamBeforeToday(message = "You cannot delete price in the past", status = HttpStatus.NOT_ACCEPTABLE)
    @Override
    public boolean delete(LocalDate date, int restaurantId, int id) {
        return repository.delete(date, id, restaurantId);
    }

    @Cacheable(value = "menu", key = "{#date, #restaurantId}")
    @Override
    public List<MenuItemTo> getAllForRestorantInDay(LocalDate date, int restaurantId) {
        return MenuUtil.convertToMenuItemTos(repository.getAllForRestorantInDay(restaurantId, date));
    }

}
