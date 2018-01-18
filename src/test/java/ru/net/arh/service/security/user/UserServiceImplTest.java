package ru.net.arh.service.security.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.net.arh.model.User;
import ru.net.arh.service.AbstractServiceTest;
import ru.net.arh.service.UserService;
import ru.net.arh.utils.validation.exception.ValidationException;

import static org.junit.Assert.assertEquals;
import static ru.net.arh.testdata.TestDBData.USER;
import static ru.net.arh.testdata.UserTestData.*;
import static ru.net.arh.web.JsonUtil.assertThat;

public class UserServiceImplTest extends AbstractServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void registerTest() throws Exception {
        User created = userService.register(NEW_USER);
        created.setPassword("");
        assertEquals(CREATED_WITHOUT_PASSWORD, created);
    }

    @Test
    public void registerWithDuplicateNameTest() throws Exception {
        thrown.expect(ValidationException.class);
        userService.register(USER_WITH_THE_SAME_NAME);
    }

    @Test
    public void loadUserByUsername() throws Exception {
        User user = (User) userService.loadUserByUsername(USER.getUsername());
        assertThat(USER, user);
    }

    @Test
    public void findWithNoResults() throws Exception {
        thrown.expect(UsernameNotFoundException.class);
        userService.loadUserByUsername("NoSuchUsername");
    }


}