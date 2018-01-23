package ru.net.arh.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.net.arh.model.MenuItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DataJpaMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    int deleteByIdAndDateAndRestaurantId(int id, LocalDate date, int restaurantId);

    List<MenuItem> getAllByRestaurantIdAndDateOrderByDishId(int restaurantId, LocalDate date);

    Optional<MenuItem> getByIdAndRestaurantIdAndDate(int id, int restaurantId, LocalDate date);
}
