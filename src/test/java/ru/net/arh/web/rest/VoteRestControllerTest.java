package ru.net.arh.web.rest;

import org.junit.After;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import ru.net.arh.utils.VoteUtil;
import ru.net.arh.web.AbstractRestTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.web.JsonUtil.assertThat;
import static ru.net.arh.web.rest.RootRest.ROOT_URL;

public class VoteRestControllerTest extends AbstractRestTest {

    @Override
    protected String getUri() {
        return ROOT_URL + "/restaurants/" + RESTAURANT1.getId() + "/votes";
    }

    @After
    public void after() {
        VoteUtil.setCanRevoteUtilTime(LocalTime.of(11, 0));
    }

    @Test
    @WithUserDetails(value = "User3", userDetailsServiceBeanName = "userService")
    public void vote() throws Exception {
        mockMvc.perform(post(getUri()))
                .andDo(print())
                .andExpect(status().isNoContent())
        ;
    }

    @Test
    @WithUserDetails(value = "User", userDetailsServiceBeanName = "userService")
    public void revoteWhenCanRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MAX);
        mockMvc.perform(post(getUri()))
                .andDo(print())
                .andExpect(status().isNoContent())
        ;
    }

    @Test
    @WithUserDetails(value = "User", userDetailsServiceBeanName = "userService")
    public void revoteWhenCannotRevote() throws Exception {
        VoteUtil.setCanRevoteUtilTime(LocalTime.MIN);
        mockMvc.perform(post(getUri()))
                .andDo(print())
                .andExpect(status().isNotModified())
        ;
    }

    @Test
    @WithAnonymousUser
    public void voteAnonimously() throws Exception {
        mockMvc.perform(post(getUri()))
                .andDo(print())
                .andExpect(status().isUnauthorized())
        ;
    }

    @Test
    @WithUserDetails(value = "Admin", userDetailsServiceBeanName = "userService")
    public void voteWihoutRole() throws Exception {
        mockMvc.perform(post(getUri()))
                .andDo(print())
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    public void getVotesCount() throws Exception {
        mockMvc.perform(get(getUri()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(TODAY_RESTAURANT1_COUNT))
        ;
    }

    @Test
    @WithAnonymousUser
    public void getVotesCountForDate() throws Exception {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        mockMvc.perform(get(getUri() + "/" + yesterday.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(YESTERDAY_RESTAURANT1_COUNT))

        ;
    }

}
