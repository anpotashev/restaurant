package ru.net.arh.service.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.DishRepository;
import ru.net.arh.service.AbstractBaseService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class DishService implements AbstractBaseService<Dish> {
    @Autowired
    private DishRepository dishRepository;

    public Dish get(int id) {
        return null;
    }

    public Dish save(Dish dish) {
        return null;
    }

    public void delete(Dish dish) {
    }

    public List<Dish> getAll() {
        return null;
    }
}
