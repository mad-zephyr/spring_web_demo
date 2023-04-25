package com.springweb.app.restservice.VersioningPerson.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersonV2 extends Person {
    private Name name;

    public PersonV2(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonV2{" +
                "name=" + name +
                "} " + super.toString();
    }
}
