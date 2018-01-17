package ru.net.arh.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.net.arh.model.Price;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DataJpaPriceRepository extends JpaRepository<Price, Integer> {

    int deleteByIdAndDateAndRestaurantId(int id, LocalDate date, int restaurantId);

    List<Price> getAllByRestaurantIdAndDateOrderByDishId(int restaurantId, LocalDate date);

    Optional<Price> getByIdAndRestaurantIdAndDate(int id, int restaurantId, LocalDate date);
}
