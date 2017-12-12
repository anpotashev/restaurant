package ru.net.arh.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Restaurant;
import ru.net.arh.model.Vote;
import ru.net.arh.to.vote.DayVotes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DayVoteUtil {
    public static DayVotes convert(List<Vote> votes, LocalDate date) {
        Map<Restaurant, Long> map = votes.stream()
                .collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));
        return new DayVotes(date, map);
    }

    public static DayVotes convert1(List<Object[]> restaurants, LocalDate date) {
        Map<Restaurant, Long> map = new HashMap<>();
        restaurants.forEach(objects -> map.put((Restaurant) objects[0], (Long) objects[1]));
        return new DayVotes(date, map);
    }
}
