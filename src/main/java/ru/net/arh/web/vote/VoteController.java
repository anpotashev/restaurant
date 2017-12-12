package ru.net.arh.web.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.net.arh.AuthorizedUser;
import ru.net.arh.service.VoteService;

import java.time.LocalDate;

@Controller
public class VoteController {

    @Autowired
    private VoteService voteService;

    public void vote(int restaurantId) {
        voteService.save(AuthorizedUser.key(), restaurantId);
    }

    public int getVotesCount(int restauarantId, LocalDate date) {
        return voteService.getRestaurantVotesForDay(restauarantId, date);
    }

    public int getVotesCount(int restauarantId) {
        return voteService.getRestaurantVotesForDay(restauarantId, LocalDate.now());
    }
}
