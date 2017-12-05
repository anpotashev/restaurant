package ru.net.arh.repository.datajpa.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.model.User;
import ru.net.arh.repository.UserRepository;

import java.util.List;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private DatajpaUserRepository repository;

    @Override
    public User find(Integer key) {
        return repository.findByKey(key);
    }

    @Override
    public User create(User value) {
        return repository.save(value);
    }

    @Override
    public User update(User value) {
        return !checkExist(value.getKey()) ? null : repository.save(value);
    }

    @Override
    public boolean delete(Integer key) {
        return repository.deleteByKey(key) > 0;
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    private boolean checkExist(int key) {
        return repository.getOne(key) != null;
    }
}
