package ru.net.arh.repository.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.RestaurantRepository;

import java.util.List;

@Slf4j
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @Autowired
    private DataJpaRestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName) {
        return restaurantRepository.findAllByNameIgnoreCaseIsStartingWith(firstPartOfName);
    }

    @Override
    public Restaurant find(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant save(Restaurant value) {
        if (!value.isNew() && find(value.getId()) == null)
            return null;
        return restaurantRepository.save(value);
    }

    @Override
    public boolean delete(int id) {
        return restaurantRepository.deleteById(id) > 0;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
