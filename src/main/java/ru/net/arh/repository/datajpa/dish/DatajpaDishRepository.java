package ru.net.arh.repository.datajpa.dish;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Dish;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DatajpaDishRepository extends JpaRepository<Dish, Integer> {

    Optional<Dish> findByKey(int key);

    List<Dish> findAll(Sort sort);

    @Transactional
    int deleteByKey(int key);

    @Transactional
    Dish save(Dish dish);

    @Query("select d from Dish d where lower(d.name) like concat(lower(:firstPartOfName), '%') order by d.name")
    public List<Dish> findAllByNameStartingWithIgnoreCase(@Param("firstPartOfName") String firstPartOfName);
}
