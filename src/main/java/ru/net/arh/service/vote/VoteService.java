package ru.net.arh.service.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Vote;
import ru.net.arh.repository.jpa.VoteRepositoryImpl;
import ru.net.arh.utils.ValidationUnit;

import java.time.LocalDate;
import java.util.List;

import static ru.net.arh.utils.VoteUtil.canRevote;

@Slf4j
@Service
public class VoteService {

    @Autowired
    private VoteRepositoryImpl repository;

    public Vote save(int user_id, int restaurant_id) {
        return ValidationUnit.checkNotFound(
                canRevote() ? repository.updateOrCreate(user_id, restaurant_id) : repository.create(user_id, restaurant_id)
                , user_id);
    }

    public List<Vote> getAllForToday() {
        return getAllForDay(LocalDate.now());
    }

    public List<Vote> getAllForDay(LocalDate date) {
        return repository.findAll(date);
    }
}
