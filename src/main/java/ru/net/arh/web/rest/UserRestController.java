package ru.net.arh.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.service.UserService;
import ru.net.arh.to.user.UserTO;

import javax.validation.Valid;

@RestController
@RequestMapping(RootRest.ROOT_URL + "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid UserTO userTo) {
        userService.register(userTo);
        return ResponseEntity.noContent().build();
    }
}
