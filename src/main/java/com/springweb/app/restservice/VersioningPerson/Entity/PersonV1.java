package com.springweb.app.restservice.VersioningPerson.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonV1 extends Person {
    private String name;
    public PersonV1(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
