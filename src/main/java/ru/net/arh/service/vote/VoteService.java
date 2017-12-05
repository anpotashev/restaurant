package ru.net.arh.service.vote;

import ru.net.arh.model.Vote;
import ru.net.arh.to.vote.DayVotes;

import java.time.LocalDate;

public interface VoteService {
    Vote save(int userId, int restaurantId);

    DayVotes getDaysVotes(LocalDate date);
}
