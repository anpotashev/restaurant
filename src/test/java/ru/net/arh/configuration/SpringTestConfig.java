package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.net.arh.model.Dish;

@Slf4j
@Configuration
public class SpringTestConfig {
    @Bean
    public Dish getDish() {
        return new Dish(1, "dish");
    }
}
