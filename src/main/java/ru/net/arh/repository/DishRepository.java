package ru.net.arh.repository;

import ru.net.arh.model.Dish;


public interface DishRepository extends AbstractRepository<Dish> {
    Dish get(Integer id);
}
