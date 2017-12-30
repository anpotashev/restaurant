package ru.net.arh.repository.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.net.arh.model.Role;
import ru.net.arh.model.User;
import ru.net.arh.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

import static ru.net.arh.model.Role.ROLE_USER;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private DataJpaUserRepository userReposiroty;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userReposiroty.findByUsernameIgnoreCase(username).orElse(null);
    }

    @Override
    public User registerNew(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(new HashSet<>(Arrays.asList(new Role[]{ROLE_USER})));
        return userReposiroty.save(user);
    }
}
