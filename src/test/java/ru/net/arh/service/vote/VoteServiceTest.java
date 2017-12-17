package ru.net.arh.service.vote;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.service.BaseTest;
import ru.net.arh.service.VoteService;
import ru.net.arh.utils.VoteUtil;
import ru.net.arh.utils.exception.DuplicateValueForUniqueIndexException;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.testdata.VoteTestData.NEW_VOTE;
import static ru.net.arh.testdata.VoteTestData.VOTE_AFTER_CHANGE;

public class VoteServiceTest extends BaseTest {
    @Autowired
    private VoteService service;

    @Test
    public void voteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(NEW_VOTE.getKey().getUser().getId(), NEW_VOTE.getRestaurant().getId());
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(3);
    }

    @Test
    public void voteWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        service.save(NEW_VOTE.getKey().getUser().getId(), NEW_VOTE.getRestaurant().getId());
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(3);
    }

    @Test
    public void revoteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(VOTE_AFTER_CHANGE.getKey().getUser().getId(), VOTE_AFTER_CHANGE.getRestaurant().getId());
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(1);
        counts = service.getRestaurantVotesForDay(RESTAURANT2.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(2);
    }

    @Test
    public void revoteWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        thrown.expect(DuplicateValueForUniqueIndexException.class);
        service.save(VOTE_AFTER_CHANGE.getKey().getUser().getId(), VOTE_AFTER_CHANGE.getRestaurant().getId());
    }


    @Test
    public void getVotesCountsForRestaurantForDay() throws Exception {
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(2);
    }

    @Test
    public void getVotesCountsForRestaurantForDayWithZeroResult() throws Exception {
        int counts = service.getRestaurantVotesForDay(RESTAURANT4.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(0);
    }
}