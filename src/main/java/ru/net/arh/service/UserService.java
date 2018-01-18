package ru.net.arh.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.net.arh.model.User;
import ru.net.arh.to.user.UserTO;

public interface UserService extends UserDetailsService {
    User register(UserTO userTO);

//    User loadUserByUsername(String username);

}
