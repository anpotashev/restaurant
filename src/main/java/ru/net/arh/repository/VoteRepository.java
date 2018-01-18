package ru.net.arh.repository;

import ru.net.arh.model.Vote;

import java.time.LocalDate;

public interface VoteRepository {

    Vote create(int userId, int restaurantId);

    Vote updateOrCreate(int userId, int restaurantId);

    int getRestaurantVotesForDay(int restaurantId, LocalDate date);

}
