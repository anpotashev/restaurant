package ru.net.arh.service.vote;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.service.BaseTest;
import ru.net.arh.to.vote.DayVotes;
import ru.net.arh.utils.VoteUtil;
import ru.net.arh.utils.exception.CreateWithFoundKeyException;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.testdata.VoteTestData.*;

public class VoteServiceTest extends BaseTest {
    @Autowired
    private VoteService service;

    @Test
    public void voteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(NEW_VOTE.getKey().getUser().getKey(), NEW_VOTE.getRestaurant().getKey());
        DayVotes dayVotes = service.getDaysVotes(LocalDate.now());
        DayVotes expected = getExpectedDayVotesForToday(VOTE1, VOTE2, VOTE3, NEW_VOTE);
        assertMatch(dayVotes, expected);

    }

    @Test
    public void voteWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        service.save(NEW_VOTE.getKey().getUser().getKey(), NEW_VOTE.getRestaurant().getKey());
        DayVotes dayVotes = service.getDaysVotes(LocalDate.now());
        DayVotes expected = getExpectedDayVotesForToday(VOTE1, VOTE2, VOTE3, NEW_VOTE);
        assertMatch(dayVotes, expected);
    }

    @Test
    public void revoteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(VOTE_AFTER_CHANGE.getKey().getUser().getKey(), VOTE_AFTER_CHANGE.getRestaurant().getKey());
        DayVotes dayVotes = service.getDaysVotes(LocalDate.now());
        DayVotes expected = getExpectedDayVotesForToday(VOTE_AFTER_CHANGE, VOTE2, VOTE3);
        assertMatch(dayVotes, expected);
    }

    @Test
    public void revoteWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        thrown.expect(CreateWithFoundKeyException.class);
        service.save(VOTE_AFTER_CHANGE.getKey().getUser().getKey(), VOTE_AFTER_CHANGE.getRestaurant().getKey());
    }

    @Test
    public void getDayVotes() {
        DayVotes dayVotes = service.getDaysVotes(LocalDate.now());
        DayVotes expected = getExpectedDayVotesForToday(VOTE1, VOTE2, VOTE3);
        assertMatch(dayVotes, expected);
    }
}