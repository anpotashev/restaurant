package ru.net.arh.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.net.arh.BaseTest;
import ru.net.arh.configuration.SpringConfig;
import ru.net.arh.configuration.SpringSecurityConfig;
import ru.net.arh.configuration.SpringTestConfig;
import ru.net.arh.configuration.WebConfig;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.net.arh.web.JsonUtil.assertThat;

@ContextConfiguration(classes = {
        SpringConfig.class
        , SpringSecurityConfig.class
        , WebConfig.class
        , SpringTestConfig.class
})
@WebAppConfiguration
@EnableWebSecurity
public abstract class AbstractRestTestClass extends BaseTest {

//    protected static final String BASE_REST_URI = RootRestController.ROOT_URL;

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppCtx;

    protected abstract String getUri();

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppCtx)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser(authorities = {"ROLE_USER"})
    protected void checkAll(Object... expected) throws Exception {
        mockMvc.perform(get(getUri()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(expected))
        ;
    }

    protected void checkAll(List expected) throws Exception {
        mockMvc.perform(get(getUri()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(expected))
        ;
    }
}
