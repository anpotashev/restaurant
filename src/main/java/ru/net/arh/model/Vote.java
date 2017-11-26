package ru.net.arh.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode
@Entity
@IdClass(VoteId.class)
@Table(name = "vote",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "vote_unique_user_date_idx")})
public class Vote {

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @Id
    private User user;

    @Column(name = "date")
    @NotNull
    @NotBlank
    @Id
    private LocalDate date;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    public Vote(User user, LocalDate date, Restaurant restaurant) {
        this.user = user;
        this.date = date;
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "user=" + user +
                ", date=" + date +
                ", restaurant=" + restaurant +
                '}';
    }
}
