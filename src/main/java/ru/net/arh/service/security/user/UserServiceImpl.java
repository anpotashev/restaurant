package ru.net.arh.service.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.User;
import ru.net.arh.repository.UserRepository;
import ru.net.arh.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User register(User user) {
        return repository.registerNew(user);
    }
}
