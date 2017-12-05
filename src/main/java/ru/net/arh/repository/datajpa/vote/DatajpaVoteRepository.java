package ru.net.arh.repository.datajpa.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.net.arh.model.Vote;
import ru.net.arh.model.VoteId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DatajpaVoteRepository extends JpaRepository<Vote, VoteId> {

    @Transactional
    Vote save(Vote vote);

    Optional<Vote> findByKey(VoteId key);


    List<Vote> findAllByKeyDate(LocalDate date);
}
