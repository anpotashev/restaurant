package ru.net.arh.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static String writeValue(Object value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }

    public static ResultMatcher assertThat(Object expected) throws JsonProcessingException {
        return content().json(writeValue(expected));
    }

    public static ResultMatcher assertThat(Object... expected) throws JsonProcessingException {
        return content().json(writeValue(Arrays.asList(expected)));
    }

    public static ResultMatcher assertThat(List expected) throws JsonProcessingException {
        return content().json(writeValue(expected));
    }

}
