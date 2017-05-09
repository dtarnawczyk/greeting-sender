package org.greetings.sender.service;

import org.greetings.sender.repository.Person;

import java.util.List;

public interface SendService {
    void sendGreetings(List<Person> receivers);
}
