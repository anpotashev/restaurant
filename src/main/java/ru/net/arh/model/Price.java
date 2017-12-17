package ru.net.arh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import ru.net.arh.model.mapped.AbstractBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = Price.DELETE_QUERY_NAME, query = "delete from Price p where p.restaurant.id = :restaurantId and" +
                " p.dish.id = :dishId and p.date = :date")
        , @NamedQuery(name = Price.GET_ALL_FOR_RESTAURANT_FOR_DATE_QUERY_NAME, query = "select p from Price p where" +
        " p.restaurant.id = :restaurantId and p.date = :date order by p.dish.id asc")
        , @NamedQuery(name = Price.CHECK_EXISTS, query = "select count(p) from Price p WHERE p.restaurant.id=:restaurantId" +
        " AND p.dish.id=:dishId AND p.date=:date")

//        , @NamedQuery(name = Dish.FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME, query = "select d from Dish d " +
//        "where lower(d.name) like lower(concat(:firstPartOfName, '%') ) order by d.id asc")
})
public class Price extends AbstractBaseEntity {
    public static final String GET_ALL_FOR_RESTAURANT_FOR_DATE_QUERY_NAME = "Price.getPricesForRestaurantForDate";
    static final String DELETE_QUERY_NAME = "Price.delete";
    public static final String CHECK_EXISTS = "Price.check_exists";
    private static final MathContext PRICE_ROUNT = new MathContext(4, RoundingMode.HALF_UP);

    @JsonIgnore
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @NotFound(action = NotFoundAction.IGNORE)
    Restaurant restaurant;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id")
    @NotFound(action = NotFoundAction.IGNORE)
    Dish dish;

    @Column(name = "date")
    @NotNull
    LocalDate date;

    @Column(name = "price", precision = 2)
    @NotNull
    @DecimalMin("0.0001")
    private BigDecimal price;

    public Price(Restaurant restaurant, Dish dish, LocalDate date, double price) {
        super();
        this.restaurant = restaurant;
        this.dish = dish;
        this.date = date;
        this.price = new BigDecimal(price).round(PRICE_ROUNT);
    }

    @Override
    public String toString() {
        return "Price{" +
                "restaurantId=" + getRestaurant().getId() +
                ", dishId=" + getDish().getId() +
                ", date=" + getDate() +
                ", price=" + price +
                '}';
    }

}
