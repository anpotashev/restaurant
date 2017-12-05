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
        uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "dish_id", "date"}, name = "price_unique_restaurant_dish_date_idx")})

public class Price implements PrimaryKeyGettable<PriceId> {
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
                ", date=" + key.getDate() +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean isNew() {
        throw new UnsupportedOperationException("not supported in this class");
    }

}
