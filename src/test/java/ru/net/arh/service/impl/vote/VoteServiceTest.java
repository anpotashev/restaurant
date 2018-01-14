package ru.net.arh.service.impl.vote;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.service.AbstractServiceTest;
import ru.net.arh.service.VoteService;
import ru.net.arh.utils.VoteUtil;
import ru.net.arh.utils.validation.exception.ValidationException;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.testdata.VoteTestData.NEW_VOTE;
import static ru.net.arh.testdata.VoteTestData.VOTE_AFTER_CHANGE;

public class VoteServiceTest extends AbstractServiceTest {
    @Autowired
    private VoteService service;

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void voteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(NEW_VOTE.getKey().getUser().getId(), NEW_VOTE.getRestaurant().getId());
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(3);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void voteWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        service.save(NEW_VOTE.getKey().getUser().getId(), NEW_VOTE.getRestaurant().getId());
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(3);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void revoteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(VOTE_AFTER_CHANGE.getKey().getUser().getId(), VOTE_AFTER_CHANGE.getRestaurant().getId());
        int counts = service.getRestaurantVotesForDay(RESTAURANT1.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(1);
        counts = service.getRestaurantVotesForDay(RESTAURANT2.getId(), LocalDate.now());
        Assertions.assertThat(counts).isEqualTo(2);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_USER"})
    public void revoteWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        thrown.expect(ValidationException.class);
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