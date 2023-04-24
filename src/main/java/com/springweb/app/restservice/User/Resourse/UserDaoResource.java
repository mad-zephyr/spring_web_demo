package com.springweb.app.restservice.User.Resourse;

import com.springweb.app.restservice.User.Entity.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Data
@Component
public class UserDaoResource {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User("Adam", LocalDate.now().minusYears(30)));
        users.add(new User("Eva", LocalDate.now().minusYears(20)));
        users.add(new User("Albert", LocalDate.now().minusYears(23)));
    }

    public List<User> getUsers() {
        return users;
    }

    public User setUsers(User user) {
        users.add(user);
        return user;
    }

    public UUID deleteUser(UUID id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        boolean isDeleted = users.removeIf(predicate);
        return isDeleted ? id : null;
    }

}
