package ru.net.arh.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.net.arh.model.User;

import java.util.Optional;

public interface DataJpaUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameIgnoreCase(String username);

}
