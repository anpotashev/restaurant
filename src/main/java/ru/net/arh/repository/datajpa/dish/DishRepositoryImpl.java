package ru.net.arh.repository.datajpa.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.DishRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private DatajpaDishRepository repository;

    @Override
    public List<Dish> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName) {
        return repository.findAllByNameStartingWithIgnoreCase(firstPartOfName);
    }

    @Override
    public Dish find(Integer key) {
        return repository.findByKey(key).orElse(null);
    }

    @Override
    public Dish create(Dish value) {
        return repository.save(value);
    }

    @Override
    public Dish update(Dish value) {
        return find(value.getKey()) == null ? null : repository.save(value);
    }

    @Override
    public boolean delete(Integer key) {
        return repository.deleteByKey(key) > 0;
    }

    @Override
    public List<Dish> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "key"));
    }
}
