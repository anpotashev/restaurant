package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ComponentScan({"ru.net.arh.**.repository.jpa", "ru.net.arh.**.service", "ru.net.arh.**.web"})
public class SpringConfig {

}
