package ru.net.arh.model;

import lombok.*;
import ru.net.arh.model.key.VoteId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
//        (name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "vote_unique_user_date_idx")})
//@NamedQueries({
//        @NamedQuery(name = "VOTE.findAllByDate", query = "select v from Vote v where v.key.date=:date order by v.restaurant.id, v.key.user.id")
//})

@NamedQueries({
        @NamedQuery(name = Vote.DELETE_QUERY_NAME, query = "delete from Vote v where v.key.user.id = :userId and" +
                " v.key.date = :date")
        , @NamedQuery(name = Vote.GET_VOTES_COUNT_FOR_RESTAURANTS_AND_DATE_QUERY_NAME, query = "select count(v) from" +
        " Vote v where v.restaurant.id = :restaurantId and v.key.date = :date")
//        , @NamedQuery(name = Dish.FIND_ALL_QUERY_NAME, query = "select d from Dish d order by d.id asc")
//        , @NamedQuery(name = Dish.FIND_ALL_BY_FIRST_PART_OF_NAME_QUERY_NAME, query = "select d from Dish d " +
//        "where lower(d.name) like lower(concat(:firstPartOfName, '%') ) order by d.id asc")
})
public class Vote implements DeleteNamedQueryExists {

    public static final String GET_VOTES_COUNT_FOR_RESTAURANTS_AND_DATE_QUERY_NAME = "Vote.getVotesCountForRestaurantInDate";
    static final String DELETE_QUERY_NAME = "Vote.delete";
//    public static final String VOTE_FIND_ALL_BY_DATE = "VOTE.findAllByDate";

    @EmbeddedId
    private VoteId key;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    public Vote(User user, LocalDate date, Restaurant restaurant) {
        key = new VoteId(user, date);
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "userId=" + key.getUser().getId() +
                ", date=" + key.getDate() +
                ", restaurantId=" + restaurant.getId() +
                '}';
    }

}
