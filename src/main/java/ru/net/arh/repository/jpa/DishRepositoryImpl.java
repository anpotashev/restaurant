package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.DishRepository;

import java.util.List;

@Slf4j
@Repository
public class DishRepositoryImpl implements DishRepository {

    @Override
    public Dish get(Integer key) {
        return null;
    }

    @Override
    public Dish save(Dish value) {
        return null;
    }

    @Override
    public List<Dish> getAll() {
        return null;
    }

    @Override
    public boolean delete(Dish value) {
        return false;
    }
}
