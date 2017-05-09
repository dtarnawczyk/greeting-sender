package org.greetings.sender.repository;

import java.util.Date;

public class People {

    private final String name;
    private final Date birthday;

    public People(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }
}
