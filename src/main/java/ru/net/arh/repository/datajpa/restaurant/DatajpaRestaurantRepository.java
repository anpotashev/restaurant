package ru.net.arh.repository.datajpa.restaurant;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Restaurant;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DatajpaRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    Optional<Restaurant> findByKey(int key);

    List<Restaurant> findAll(Sort sort);

    @Transactional
    int deleteByKey(int key);

    @Transactional
    Restaurant save(Restaurant dish);

    @Query("select r from Restaurant r where lower(r.name) like concat(lower(:firstPartOfName), '%') order by r.name")
    public List<Restaurant> findAllByNameStartingWithIgnoreCase(@Param("firstPartOfName") String firstPartOfName);
}
