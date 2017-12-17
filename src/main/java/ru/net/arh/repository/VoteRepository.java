package ru.net.arh.repository;

import ru.net.arh.model.Vote;

import java.time.LocalDate;

public interface VoteRepository {

    Vote create(final int userId, final int restaurantId);

    Vote updateOrCreate(final int userId, final int restaurantId);

    int getRestaurantVotesForDay(final int restaurantId, final LocalDate date);

}
