package ru.net.arh.service.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Role;
import ru.net.arh.model.User;
import ru.net.arh.repository.UserRepository;
import ru.net.arh.service.UserService;
import ru.net.arh.to.user.UserTO;
import ru.net.arh.utils.validation.annotation.CheckForException;
import ru.net.arh.utils.validation.annotation.CheckForNullResult;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @CheckForException(message = "Cannot register user", status = HttpStatus.NOT_ACCEPTABLE)
    @CheckForNullResult(message = "Cannot register user", status = HttpStatus.NOT_ACCEPTABLE)
    @Override
    public User register(UserTO userTO) {
        User user = new User(userTO.getUsername(), userTO.getPassword(), Role.ROLE_USER);
        return repository.registerNew(user);
    }

    @Override
    @Cacheable("users")
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
        return user;
    }
}
