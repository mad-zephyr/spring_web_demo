package com.springweb.app.restservice.VersioningPerson.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotImplemented extends RuntimeException {
    public NotImplemented(String message) {
        super(message);
    }
}
