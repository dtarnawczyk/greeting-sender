package org.greetings.sender.repository;

import java.time.LocalDate;

public class Person {

    private final String name;
    private final LocalDate birthday;
    private final String email;

    public Person(String name, LocalDate birthday, String email) {
        this.name = name;
        this.birthday = birthday;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }
}
