package ru.net.arh.service;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import ru.net.arh.BaseTest;
import ru.net.arh.configuration.SpringConfig;
import ru.net.arh.configuration.SpringSecurityConfig;
import ru.net.arh.configuration.SpringTestConfig;


@ContextConfiguration(classes = {
        SpringConfig.class
        , SpringSecurityConfig.class
        , SpringTestConfig.class
})
public abstract class AbstractServiceTest extends BaseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
