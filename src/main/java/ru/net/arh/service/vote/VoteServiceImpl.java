package ru.net.arh.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Vote;
import ru.net.arh.repository.VoteRepository;
import ru.net.arh.service.VoteService;

import java.time.LocalDate;

import static ru.net.arh.utils.VoteUtil.canRevote;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote save(int userId, int restaurantId) {
        return canRevote()
                ? repository.updateOrCreate(userId, restaurantId)
                : repository.create(userId, restaurantId);
    }

    @Override
    public int getRestaurantVotesForDay(int restaurantId, LocalDate date) {
        return repository.getRestaurantVotesForDay(restaurantId, date);
    }

}
