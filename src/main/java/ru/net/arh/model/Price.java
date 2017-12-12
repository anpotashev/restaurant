package ru.net.arh.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.key.PriceId;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table
//        (name = "price", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_id", "date"}, name = "price_unique_restaurant_dish_date_idx")})
@NamedQueries({
        @NamedQuery(name = Price.DELETE_QUERY_NAME, query = "delete from Price p where p.key.restaurant.id = :restaurantId and" +
                " p.key.dish.id = :dishId and p.key.date = :date")
        , @NamedQuery(name = Price.GET_ALL_FOR_RESTAURANT_FOR_DATE_QUERY_NAME, query = "select p from Price p where" +
        " p.key.restaurant.id = :restaurantId and p.key.date = :date order by p.key.dish.id asc")
//        , @NamedQuery(name = Dish.FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME, query = "select d from Dish d " +
//        "where lower(d.name) like lower(concat(:firstPartOfName, '%') ) order by d.id asc")
})
public class Price implements DeleteNamedQueryExists {
    public static final String GET_ALL_FOR_RESTAURANT_FOR_DATE_QUERY_NAME = "Price.getPricesForRestaurantForDate";
    static final String DELETE_QUERY_NAME = "Price.delete";

    @EmbeddedId
    private PriceId key;

    @Column(name = "price")
    @NotNull
    @DecimalMin("0.0001")
    private BigDecimal price;

    public Price(Restaurant restaurant, Dish dish, LocalDate date, BigDecimal price) {
        key = new PriceId(restaurant, dish, date);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "restaurantId=" + key.getRestaurant().getId() +
                ", dishId=" + key.getDish().getId() +
                ", date=" + key.getDate() +
                ", price=" + price +
                '}';
    }

}
