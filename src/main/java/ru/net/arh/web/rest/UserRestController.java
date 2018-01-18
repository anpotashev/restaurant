package ru.net.arh.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.net.arh.model.User;
import ru.net.arh.service.UserService;

@RestController
@RequestMapping(RootRest.ROOT_URL + "/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.noContent().build();
    }
}
