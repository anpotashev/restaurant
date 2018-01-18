package ru.net.arh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Price extends AbstractBaseEntity {
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
        this(restaurant, dish, date, new BigDecimal(price).round(PRICE_ROUNT).setScale(2));
    }

    public Price(Integer id, Restaurant restaurant, Dish dish, LocalDate date, double price) {
        this(restaurant, dish, date, price);
        this.id = id;
    }

}
