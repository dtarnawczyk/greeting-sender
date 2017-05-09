package org.greetings.sender.repository;

import java.time.LocalDate;
import java.util.List;

public interface PeopleRepository {
    List<Person> findPeoplesBornOn(LocalDate date);
}
