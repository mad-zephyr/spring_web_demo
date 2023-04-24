package com.springweb.app.restservice.User.Service;

import com.springweb.app.restservice.User.Entity.User;
import com.springweb.app.restservice.User.Resourse.UserDaoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Component
public class UserService {
    private final UserDaoResource userDao;

    @Autowired
    public UserService(UserDaoResource userDao) {
        this.userDao = userDao;
    }

    public List<User> getAll() {
        return this.userDao.getUsers();
    }

    public User getOne(UUID id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return this.userDao.getUsers().stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        return this.userDao.setUsers(user);
    }
}
