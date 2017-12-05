package ru.net.arh.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Slf4j
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@IdClass(VoteId.class)
@Table(name = "vote",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "vote_unique_user_date_idx")})
@NamedQueries({
        @NamedQuery(name = "VOTE.findAllByDate", query = "select v from Vote v where v.key.date=:date order by v.restaurant.id, v.key.user.id")
})
public class Vote implements PrimaryKeyGettable<VoteId> {

    public static final String VOTE_FIND_ALL_BY_DATE = "VOTE.findAllByDate";

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
                "user=" + key.getUser() +
                ", date=" + key.getDate() +
                ", restaurant=" + restaurant +
                '}';
    }


    @Override
    public boolean isNew() {
        throw new UnsupportedOperationException("not supported in this class");
    }

}
