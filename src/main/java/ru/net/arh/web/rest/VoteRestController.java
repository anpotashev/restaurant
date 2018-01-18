package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.net.arh.AuthorizedUser;
import ru.net.arh.service.VoteService;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(RootRest.ROOT_URL + "/restaurants/{restaurantId}/votes")
public class VoteRestController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> vote(@PathVariable("restaurantId") int restaurantId) {
        voteService.save(AuthorizedUser.key(), restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Integer> votes(@PathVariable("restaurantId") int restaurantId) {
        return ResponseEntity.ok(voteService.getRestaurantVotesForDay(restaurantId, LocalDate.now()));
    }

    @GetMapping("/{date}")
    public ResponseEntity<Integer> votes(
            @PathVariable("restaurantId") int restaurantId
            , @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return ResponseEntity.ok(voteService.getRestaurantVotesForDay(restaurantId, date));
    }
}
