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
        @NamedQuery(name = "PRICE.delete", query = "delete from Price p where p.priceKey.dish.id = :dish_id and p.priceKey.restaurant.id = :restaurant_id and p.priceKey.startDate = :startDate")
})
public class Price implements PrimaryKeyGettable<PriceId> {
    public static final String DELETE = "PRICE.delete";
    @EmbeddedId
    private PriceId priceKey;


//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
//    @Id
//    private Restaurant restaurant;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "dish_id")
//    @Id
//    private Dish dish;
//
//    @Column(name = "start_date")
//    @NotNull
//    @NotBlank
//    @Id
//    private LocalDate startDate;

    @Column(name = "price")
    @NotNull
    @NotBlank
    @DecimalMin("0.0001")
    private BigDecimal price;

    public Price(Restaurant restaurant, Dish dish, LocalDate date, BigDecimal price) {
        priceKey = new PriceId(restaurant, dish, date);
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "restaurant=" + priceKey.getRestaurant() +
                ", dish=" + priceKey.getDish() +
                ", startDate=" + priceKey.getStartDate() +
                ", price=" + price +
                '}';
    }

    @Override
    public PriceId getKey() {
        return priceKey;
    }
}
