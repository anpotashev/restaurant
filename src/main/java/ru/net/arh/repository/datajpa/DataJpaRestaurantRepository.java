package ru.net.arh.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DataJpaRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> findById(int id);

    @Transactional
    int deleteById(int id);

    @Transactional
    Restaurant save(Restaurant restaurant);

    List<Restaurant> findAllByNameIgnoreCaseIsStartingWith(String name);

}
