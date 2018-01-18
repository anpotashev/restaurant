package ru.net.arh.testdata;

import lombok.extern.slf4j.Slf4j;
import ru.net.arh.model.Role;
import ru.net.arh.model.User;
import ru.net.arh.to.user.UserTO;

import static ru.net.arh.testdata.TestDBData.NEXT_ID;

@Slf4j
public class UserTestData {

    public static final UserTO NEW_USER = new UserTO("NewUser", "password");
    public static final UserTO USER_WITH_THE_SAME_NAME = new UserTO("User", "password");
    public static final UserTO USER_WITH_EMPTY_PASSWORD = new UserTO("NewUserWithoutPassword", "");
    public static final User CREATED_WITHOUT_PASSWORD = new User(NEXT_ID, "NewUser", "", Role.ROLE_USER);

}
