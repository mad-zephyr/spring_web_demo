package com.springweb.app.restservice.User.Controller;


import com.springweb.app.restservice.User.Entity.User;
import com.springweb.app.restservice.User.Exceptions.UserNotFoundException;
import com.springweb.app.restservice.User.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> getById(@PathVariable UUID id) {
        User user = userService.getOne(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }

        EntityModel<User> entityModelUser =  EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModelUser.add(link.withRel("all_users"));

        return entityModelUser;
    }

    @PostMapping("users")
    public ResponseEntity<User> setUser(@Valid @RequestBody User user) {
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
