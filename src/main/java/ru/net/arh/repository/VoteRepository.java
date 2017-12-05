package ru.net.arh.repository;

import ru.net.arh.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote create(int userId, int restaurantId);

    Vote updateOrCreate(int userId, int restaurantId);

    List<Vote> getDaysVotes(LocalDate date);
}
