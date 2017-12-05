package ru.net.arh.repository.datajpa.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.RestaurantRepository;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private DatajpaRestaurantRepository repository;

    @Override
    public List<Restaurant> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName) {
        return repository.findAllByNameStartingWithIgnoreCase(firstPartOfName);
    }

    @Override
    public Restaurant find(Integer key) {
        return repository.findByKey(key).orElse(null);
    }

    @Override
    public Restaurant create(Restaurant value) {
        return repository.save(value);
    }

    @Override
    public Restaurant update(Restaurant value) {
        return find(value.getKey()) == null ? null : repository.save(value);
    }

    @Override
    public boolean delete(Integer key) {
        return repository.deleteByKey(key) > 0;
    }

    @Override
    public List<Restaurant> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "key"));
    }
}
