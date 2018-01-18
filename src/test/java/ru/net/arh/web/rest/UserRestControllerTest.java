package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.net.arh.web.AbstractRestTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.net.arh.testdata.UserTestData.*;
import static ru.net.arh.web.JsonUtil.writeValue;

@Slf4j
@Sql(scripts = "classpath:db/data.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserRestControllerTest extends AbstractRestTest {


    @Test
    public void register() throws Exception {
        log.debug(writeValue(NEW_USER));
        mockMvc.perform(post(getUri()).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(NEW_USER))
        )
                .andDo(print())
                .andExpect(status().isNoContent())

        ;
    }

    @Test
    public void registerWithEmptyPassword() throws Exception {
        mockMvc.perform(post(getUri()).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(USER_WITH_EMPTY_PASSWORD))
        )
                .andDo(print())
                .andExpect(status().isBadRequest())

        ;
    }


    @Test
    public void registerWithTheSameName() throws Exception {
        mockMvc.perform(post(getUri()).contentType(
                MediaType.APPLICATION_JSON_UTF8)
                .content(writeValue(USER_WITH_THE_SAME_NAME))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Override
    protected String getUri() {
        return RootRest.ROOT_URL + "/users";
    }
}