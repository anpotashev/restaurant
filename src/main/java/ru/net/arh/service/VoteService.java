package ru.net.arh.service;

import ru.net.arh.model.Vote;

import java.time.LocalDate;

public interface VoteService {
    Vote save(int userId, int restaurantId);

    int getRestaurantVotesForDay(int restaurantId, LocalDate date);

    /*
    Vote create(int userId, int restaurantId);

    Vote updateOrCreate(int userId, int restaurantId);

    int getRestaurantVotesForDay(int restaurantId, LocalDate date);
     */
}
