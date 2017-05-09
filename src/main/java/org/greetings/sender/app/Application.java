package org.greeting.sender.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{

    private BirthdayService emailService;
    private PeopleRepository repository;

    @Override
    public void run(String... args) {
        BirthdayService birthdayService = new BirthdayService(
                PeopleRepository, emailService);
        birthdayService.sendGreetings(today());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
