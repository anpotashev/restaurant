package ru.net.arh.to.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.net.arh.model.Restaurant;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@Getter
public class DayVotes {
    private final LocalDate date;
    private final Map<Restaurant, Long> votes;
}
