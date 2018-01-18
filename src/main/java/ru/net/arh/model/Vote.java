package ru.net.arh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.net.arh.model.key.VoteId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = Vote.GET_VOTES_COUNT_FOR_RESTAURANTS_AND_DATE_QUERY_NAME, query = "select count(v) from" +
                " Vote v where v.restaurant.id = :restaurantId and v.key.date = :date")
})
public class Vote {

    public static final String GET_VOTES_COUNT_FOR_RESTAURANTS_AND_DATE_QUERY_NAME = "Vote.getVotesCountForRestaurantInDate";

    @EmbeddedId
    private VoteId key;

    @Setter
    @ManyToOne
    @NotNull
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    public Vote(User user, LocalDate date, Restaurant restaurant) {
        key = new VoteId(user, date);
        this.restaurant = restaurant;
    }


}
