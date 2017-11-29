package ru.net.arh.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
//@IdClass(PriceId.class)
@Table(name = "price",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_id", "start_date"}, name = "price_unique_restaurant_dish_date_idx")})

@NamedQueries({
        @NamedQuery(name = "PRICE.delete", query = "delete from Price p where p.key.dish.id = :dish_id and p.key.restaurant.id = :restaurant_id and p.key.startDate = :startDate")
        , @NamedQuery(name = "PRICE.findAllOnDay", query = "select p from Price p where p.key.startDate = :startDate")
        , @NamedQuery(name = "PRICE.findAllOnDayInRestaurant", query = "select p from Price p where p.key.restaurant.id = :restaurant_id and p.key.startDate = :startDate")
})
public class Price implements PrimaryKeyGettable<PriceId> {
    public static final String PRICE_DELETE = "PRICE.delete";
    public static final String PRICE_FIND_ALL_ON_DAY = "PRICE.findAllOnDay";
    public static final String PRICE_FIND_ALL_ON_DAY_IN_RESTAURANT = "PRICE.findAllOnDayInRestaurant";

    @EmbeddedId
    private PriceId key;


    @Column(name = "price")
    @NotNull
    @NotBlank
    @DecimalMin("0.0001")
    private BigDecimal price;

    public Price(Restaurant restaurant, Dish dish, LocalDate date, BigDecimal price) {
        key = new PriceId(restaurant, dish, date);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "restaurant=" + key.getRestaurant() +
                ", dish=" + key.getDish() +
                ", startDate=" + key.getStartDate() +
                ", price=" + price +
                '}';
    }

//    @Override
//    public PriceId getKey() {
//        return key;
//    }
}
