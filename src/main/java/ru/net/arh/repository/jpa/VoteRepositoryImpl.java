package ru.net.arh.repository.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.User;
import ru.net.arh.model.Vote;
import ru.net.arh.model.VoteId;
import ru.net.arh.repository.AbstractBasedRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
@Transactional(readOnly = true)
public class VoteRepositoryImpl extends AbstractBasedRepository<Vote, VoteId> {

    @Override
    public Class<Vote> getClazz() {
        return Vote.class;
    }

    @Override
    public String findAllNamedQuery() {
        return null;
    }

    @Transactional
    public Vote updateOrCreate(int user_id, int restaurant_id) {
        User user = em.getReference(User.class, user_id);
        Restaurant restaurant = em.getReference(Restaurant.class, restaurant_id);
        if (foundPK(new VoteId(user, LocalDate.now())))
            return this.update(new Vote(user, LocalDate.now(), restaurant));
        return this.create(new Vote(user, LocalDate.now(), restaurant));
    }

    @Transactional
    public Vote create(int user_id, int restaurant_id) {
        User user = em.getReference(User.class, user_id);
        Restaurant restaurant = em.getReference(Restaurant.class, restaurant_id);
        if (foundPK(new VoteId(user, LocalDate.now())))
            return null;
        return this.create(new Vote(user, LocalDate.now(), restaurant));
    }

    private boolean foundPK(VoteId voteId) {
        return em.find(Vote.class, voteId) != null;
    }

    public List<Vote> findAll(LocalDate date) {
        return em.createNamedQuery(Vote.VOTE_FIND_ALL_BY_DATE)
                .setParameter("date", date)
                .getResultList();
    }
}
