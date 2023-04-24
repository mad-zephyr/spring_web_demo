package com.springweb.app.restservice.User.Controller;


import com.springweb.app.restservice.User.Entity.User;
import com.springweb.app.restservice.User.Exceptions.UserNotFoundException;
import com.springweb.app.restservice.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("users/{id}")
    public User getById(@PathVariable UUID id) {
        User user = userService.getOne(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }

        return user;
    }

    @PostMapping("users")
    public ResponseEntity<User> setUser(@RequestBody User user) {
        User createdUser = this.userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public UUID deleteById(@PathVariable String id) {
        UUID deleteUserId = this.userService.delete(UUID.fromString(id));
        if (deleteUserId == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return deleteUserId;
    }
}
