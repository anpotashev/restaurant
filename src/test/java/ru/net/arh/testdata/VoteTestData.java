package ru.net.arh.testdata;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Vote;
import ru.net.arh.to.vote.DayVotes;
import ru.net.arh.utils.DayVoteUtil;

import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.net.arh.testdata.TestDBData.*;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteTestData {

    public static final Vote VOTE_AFTER_CHANGE = new Vote(USER, LocalDate.now(), RESTAURANT2);
    public static final Vote NEW_VOTE = new Vote(USER3, LocalDate.now(), RESTAURANT2);

    public static Vote getNew() {
        return new Vote(USER3, LocalDate.now(), RESTAURANT2);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual)
                .isEqualToComparingOnlyGivenFields(expected, "key.user.id", "key.date", "restaurant.id");
    }

    public static void assertMatch(DayVotes actual, DayVotes expected) {
        assertThat(actual)
                .isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorOnFields("key.user.id", "key.date", "restaurant.id").isEqualTo(expected);
    }

    public static DayVotes getExpectedDayVotesForToday(Vote... args) {
        return DayVoteUtil.convert(Arrays.asList(args), LocalDate.now());
    }
}
