package ru.net.arh.repository.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Dish;
import ru.net.arh.repository.DishRepository;

import java.util.List;

@Slf4j
@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private DataJpaDishRepository dishRepository;

    @Override
    public List<Dish> findAllByFirstPartOfNameIgnoringCase(String firstPartOfName) {
        return dishRepository.findAllByNameIgnoreCaseIsStartingWith(firstPartOfName);
    }

    @Override
    public Dish find(int id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish save(Dish value) {
        if (!value.isNew() && dishRepository.getOne(value.getId()) == null)
            return null;
        return dishRepository.save(value);
    }

    @Override
    public Dish findOne(int id) {
        return dishRepository.getOne(id);
    }

    @Override
    public Dish createOrUpdate(Dish value) {
        return dishRepository.save(value);
    }

//    @Override
//    public Dish update(Dish value) {
//        return dishRepository.save(value);
//    }

    @Override
    public boolean delete(int id) {
        return dishRepository.deleteById(id) > 0;
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
