package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@ComponentScan({
        "ru.net.arh.**.service"
        , "ru.net.arh.**.web"
        , "ru.net.arh.**.jpa"
})
@Import(SpringDBConfig.class)
public class SpringConfig {

}