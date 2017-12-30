package ru.net.arh.repository;

import ru.net.arh.model.User;

public interface UserRepository {
    User findByUsername(String username);

    User registerNew(User user);
}
