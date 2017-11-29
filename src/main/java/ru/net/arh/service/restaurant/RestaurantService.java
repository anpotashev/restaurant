package ru.net.arh.service.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Restaurant;
import ru.net.arh.repository.NamedBasedRepository;
import ru.net.arh.repository.jpa.RestaurantRepositoryImpl;
import ru.net.arh.service.AbstractNamedService;

@Slf4j
@Service
public class RestaurantService extends AbstractNamedService<Restaurant> {
    @Autowired
    private RestaurantRepositoryImpl repository;

    @Override
    protected NamedBasedRepository<Restaurant> getRepository() {
        return repository;
    }

//    public Restaurant get(int id) {
//        return ValidationUnit.checkNotFound(repository.find(id), id);
//    }
//
//    public Restaurant create(Restaurant restaurant) {
//        return repository.create(restaurant);
//    }
//
//    public Restaurant update(Restaurant restaurant) {
//        return ValidationUnit.checkNotFound(repository.update(restaurant), restaurant.getId());
//    }
//
//    public void delete(int id) {
//        try {
//            repository.delete(id);
//        } catch (EmptyResultDataAccessException e) {
//            ValidationUnit.checkNotFound(null, id);
//        }
//    }
//
//    public List<Restaurant> getAll() {
//        return repository.findAll();
//    }
//
//    public List<Restaurant> findStaringWithName(String name) {
//        return repository.findByName(name);
//    }
//
//    public List<Restaurant> findStaringWithNameIgnoreCase(String name) {
//        return repository.findByNameIgnoreCase(name);
//    }
}
