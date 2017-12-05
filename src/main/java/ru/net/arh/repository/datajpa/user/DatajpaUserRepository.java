package ru.net.arh.repository.datajpa.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.User;

import java.util.List;

public interface DatajpaUserRepository extends JpaRepository<User, Integer> {

    User findByKey(int key);

    @Transactional
    int deleteByKey(int key);

    @Transactional
    User save(User user);

    List<User> findAll();
}
