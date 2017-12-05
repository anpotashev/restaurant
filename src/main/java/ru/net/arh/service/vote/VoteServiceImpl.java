package ru.net.arh.service.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.net.arh.model.Vote;
import ru.net.arh.repository.VoteRepository;
import ru.net.arh.to.vote.DayVotes;
import ru.net.arh.utils.DayVoteUtil;
import ru.net.arh.utils.ValidationUnit;

import java.time.LocalDate;

import static ru.net.arh.utils.VoteUtil.canRevote;

@Slf4j
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository repository;

    @Override
    public Vote save(int userId, int restaurantId) {
        return canRevote() ? repository.updateOrCreate(userId, restaurantId)
                : ValidationUnit.checkCreateResult(repository.create(userId, restaurantId),
                "userId=" + userId
                        + ", date=" + LocalDate.now()
        );
    }

    @Override
    public DayVotes getDaysVotes(LocalDate date) {
        return DayVoteUtil.convert(repository.getDaysVotes(date), date);
    }

}
