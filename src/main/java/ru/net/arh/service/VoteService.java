package ru.net.arh.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.net.arh.model.Vote;

import java.time.LocalDate;

public interface VoteService {

    @PreAuthorize("hasRole('ROLE_USER')")
    Vote save(int userId, int restaurantId);

    int getRestaurantVotesForDay(int restaurantId, LocalDate date);

}
