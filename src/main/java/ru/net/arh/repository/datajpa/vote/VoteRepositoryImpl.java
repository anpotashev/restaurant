package ru.net.arh.repository.datajpa.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.User;
import ru.net.arh.model.Vote;
import ru.net.arh.model.VoteId;
import ru.net.arh.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private DatajpaVoteRepository repository;
    @Autowired
    private JpaRepository<User, Integer> userRepository;

    @Autowired
    private JpaRepository<Restaurant, Integer> restaurantRepository;

    @Override
    public Vote create(int userId, int restaurantId) {
        return checkExist(userId) ? null : updateOrCreate(userId, restaurantId);
    }

    @Override
    public Vote updateOrCreate(int userId, int restaurantId) {
        return repository.save(createVote(userId, restaurantId));
    }

    @Override
    public List<Vote> getDaysVotes(LocalDate date) {
        return repository.findAllByKeyDate(date);
    }

    private boolean checkExist(int userId) {
        return repository.findByKey(createVoteId(userId)).orElse(null) != null;
    }

    private Vote createVote(int userId, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        return new Vote(createVoteId(userId), restaurant);
    }

    private VoteId createVoteId(int userId) {
        User user = userRepository.getOne(userId);
        return new VoteId(user, LocalDate.now());
    }
}
