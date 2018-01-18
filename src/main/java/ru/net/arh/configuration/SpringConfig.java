package ru.net.arh.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@ComponentScan({
        "ru.net.arh.**.service.impl"
        , "ru.net.arh.**.jpa"
        , "ru.net.arh.**.datajpa"
        , "ru.net.arh.**.validation"
})
@Import({
        SpringDBConfig.class
        , SpringCacheConfig.class
})
@EnableAspectJAutoProxy
public class SpringConfig {

}
