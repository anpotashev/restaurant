package ru.net.arh.service.vote;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.net.arh.configuration.SpringConfig;
import ru.net.arh.model.Vote;
import ru.net.arh.testdata.VoteTestData;
import ru.net.arh.utils.VoteUtil;
import ru.net.arh.utils.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.testdata.VoteTestData.NEW_VOTE;
import static ru.net.arh.testdata.VoteTestData.getNew;

@ContextConfiguration(classes = SpringConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql")
@Slf4j
public class VoteServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Autowired
    private VoteService service;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllForday() {
        List<Vote> actual = service.getAllForDay(LocalDate.now().minusDays(1));
        VoteTestData.assertMatch(actual, VOTE1_YESTERDAY, VOTE2_YESTERDAY);
    }

    @Test
    public void getAllForToday() {
        List<Vote> actual = service.getAllForToday();
        VoteTestData.assertMatch(actual, VOTE1, VOTE2);
    }

    @Test
    public void changeWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        thrown.expect(NotFoundException.class);
        service.save(USER.getId(), RESTAURANT2.getId());
    }

    @Test
    public void changeWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        Vote newVote = getNew();
        service.save(newVote.getKey().getUser().getId(), newVote.getRestaurant().getId());
        List<Vote> actual = service.getAllForToday();
        VoteTestData.assertMatch(actual, VOTE1, VOTE2, NEW_VOTE);
    }

    @Test
    public void createWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        service.save(USER2.getId(), RESTAURANT2.getId());
        List<Vote> actual = service.getAllForToday();
        VoteTestData.assertMatch(actual, VOTE1, VOTE2, NEW_VOTE);
    }

    @Test
    public void createWhenCanNotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        service.save(USER2.getId(), RESTAURANT2.getId());
        List<Vote> actual = service.getAllForToday();
        VoteTestData.assertMatch(actual, VOTE1, VOTE2, NEW_VOTE);
    }
}