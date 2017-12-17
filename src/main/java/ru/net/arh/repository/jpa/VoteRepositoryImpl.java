package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.User;
import ru.net.arh.model.Vote;
import ru.net.arh.model.key.VoteId;
import ru.net.arh.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Slf4j
@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote create(final int userId, final int restaurantId) {
        if (checkExists(userId, LocalDate.now()))
            return null;
        return em.merge(getVote(userId, restaurantId, LocalDate.now()));
    }

    @Override
    @Transactional
    public Vote updateOrCreate(final int userId, final int restaurantId) {
        if (!checkExists(userId, LocalDate.now())) {
            return em.merge(getVote(userId, restaurantId, LocalDate.now()));
        }
        VoteId voteId = getVoteId(userId, LocalDate.now());

        Vote vote = em.find(Vote.class, voteId);
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        vote.setRestaurant(restaurant);
        em.persist(vote);
        return vote;
    }

    @Override
    public int getRestaurantVotesForDay(final int restaurantId, final LocalDate date) {
        return (em.createNamedQuery(Vote.GET_VOTES_COUNT_FOR_RESTAURANTS_AND_DATE_QUERY_NAME, Long.class)
                .setParameter("restaurantId", restaurantId)
                .setParameter("date", date)
                .getSingleResult()).intValue();
    }

    private VoteId getVoteId(final int userId, final LocalDate date) {
        User user = em.getReference(User.class, userId);
        return new VoteId(user, date);
    }

    private Vote getVote(final int userId, final int restaurantId, final LocalDate date) {
        VoteId voteId = getVoteId(userId, date);
        Restaurant restaurant = em.getReference(Restaurant.class, restaurantId);
        return new Vote(voteId, restaurant);
    }

    private boolean checkExists(final int userId, final LocalDate date) {
        VoteId voteId = getVoteId(userId, date);
        return em.find(Vote.class, voteId) != null;
    }

}
