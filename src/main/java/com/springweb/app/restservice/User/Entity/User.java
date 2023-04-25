package com.springweb.app.restservice.User.Entity;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class User {

    private UUID id;

    @Size(min=2, message = "Name should be minimum 2 characters")
    private String name;

    @Past(message = "Birthdate should be in the past")
    private LocalDate birthDate;

    public User(String name, LocalDate birthDate) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
