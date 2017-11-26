package ru.net.arh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PriceId {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dish_id")
    Dish dish;

    @Column(name = "start_date")
    @NotNull
    @NotBlank
    LocalDate startDate;
}
