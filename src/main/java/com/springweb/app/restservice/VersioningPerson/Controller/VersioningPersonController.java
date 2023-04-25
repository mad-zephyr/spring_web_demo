package com.springweb.app.restservice.VersioningPerson.Controller;

import com.springweb.app.restservice.VersioningPerson.Entity.Name;
import com.springweb.app.restservice.VersioningPerson.Entity.Person;
import com.springweb.app.restservice.VersioningPerson.Entity.PersonV1;
import com.springweb.app.restservice.VersioningPerson.Entity.PersonV2;
import com.springweb.app.restservice.VersioningPerson.Exceptions.NotImplemented;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("v1/person")
    public Person getPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("v2/person")
    public Person getPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
    @GetMapping(path = "person", params = "version=1")
    public Person getPersonByRequestParamV1() {
        return new PersonV1("Bob Charlie Version 1");
    }
    @GetMapping(path = "person", params = "version=2")
    public Person getPersonByRequestParamV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping("person")
    public Person getPersonByRequestParam(@Param("version") Integer version) {
        switch (version) {
            case 1 -> {
                return new PersonV1("Bob Charlie Version 1");
            }
            case 2 -> {
                return new PersonV2(new Name("Bob", "Charlie"));
            }
            default -> throw new NotImplemented("Use version parameter 1 or 2, but you using parameter: " + version);
        }
    }

    @GetMapping("person/header")
    public Person getPersonByRequestHeaders(@RequestHeader("X-API-VERSION") Integer version) {
        switch (version) {
            case 1 -> {
                return new PersonV1("Bob Charlie Version 1");
            }
            case 2 -> {
                return new PersonV2(new Name("Bob", "Charlie"));
            }
            default -> throw new NotImplemented("Use version header X-API-VERSION 1 or 2, but you using X-API-VERSION: " + version);
        }
    }

    @GetMapping(path="person/accept", produces = "application/vnd.company.app-v1+json")
    public Person getPersonByAcceptHeadersV1(){
        return new PersonV1("Bob Charlie Version 1");
    }
    @GetMapping(path="person/accept", produces = "application/vnd.company.app-v2+json")
    public Person getPersonByAcceptHeadersV2(){
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
