package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.net.arh.web.vote.VoteController;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/restaurants/{restaurantId}/votes")
public class VoteRestController {

    @Autowired
    private VoteController voteController;

    @PostMapping
    public ResponseEntity<Void> vote(@PathVariable("restaurantId") int restaurantId) {
        voteController.vote(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Integer> votes(@PathVariable("restaurantId") int restaurantId) {
        return ResponseEntity.ok(voteController.getVotesCount(restaurantId));
    }

    @GetMapping("/{date}")
    public ResponseEntity<Integer> votes(
            @PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(voteController.getVotesCount(restaurantId, date));
    }
}
