package org.greetings.sender.app;

import org.greetings.sender.repository.PeopleRepository;
import org.greetings.sender.repository.Person;
import org.greetings.sender.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "org.greetings.sender")
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private SendService sendService;

    @Autowired
    private PeopleRepository repository;

    @Override
    public void run(String... args) {
        log.debug(">>> Starting Application <<<");
        sendService.sendGreetings(today());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private List<Person> today() {
        return repository.findPeoplesBornOn(LocalDate.now());
    }

}
