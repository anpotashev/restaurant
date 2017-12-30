package ru.net.arh.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DataJpaDishRepository extends JpaRepository<Dish, Integer> {

    Optional<Dish> findById(int id);

    @Transactional
    int deleteById(int id);

    @Transactional
    Dish save(Dish dish);

    List<Dish> findAllByNameIgnoreCaseIsStartingWith(String name);

}
