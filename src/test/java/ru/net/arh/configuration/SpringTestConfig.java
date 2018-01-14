package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan("ru.net.arh.testdata")
public class SpringTestConfig {
//    @Bean
//    public Dish getDish() {
//        return new Dish(1, "dish");
//    }
}
