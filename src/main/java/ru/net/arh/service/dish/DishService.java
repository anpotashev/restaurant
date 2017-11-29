package ru.net.arh.service.dish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.NamedBasedRepository;
import ru.net.arh.repository.jpa.DishRepositoryImpl;
import ru.net.arh.service.AbstractNamedService;

@Slf4j
@Service
public class DishService extends AbstractNamedService<Dish> {
    @Autowired
    private DishRepositoryImpl repository;

    @Override
    protected NamedBasedRepository<Dish> getRepository() {
        return repository;
    }

//    public Dish get(int id) {
//        return ValidationUnit.checkNotFound(repository.find(id), id);
//    }
//
//    public Dish create(Dish dish) {
//        return repository.create(dish);
//    }
//
//    public Dish update(Dish dish) {
//        return ValidationUnit.checkNotFound(repository.update(dish), dish.getId());
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
//    public List<Dish> getAll() {
//        return repository.findAll();
//    }
//
//    public List<Dish> findStaringWithName(String name) {
//        return repository.findByName(name);
//    }
//
//    public List<Dish> findStaringWithNameIgnoreCase(String name) {
//        return repository.findByNameIgnoreCase(name);
//    }
}
