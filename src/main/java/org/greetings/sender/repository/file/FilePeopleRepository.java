package org.greetings.sender.repository.file;

import org.greetings.sender.repository.PeopleRepository;
import org.greetings.sender.repository.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FilePeopleRepository implements PeopleRepository {

    private static final Logger log = LoggerFactory.getLogger(FilePeopleRepository.class);

    private ResourceLoader resourceLoader;
    private File peopleBirthday;

    @Autowired
    public FilePeopleRepository(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        try {
            Resource resource = resourceLoader.getResource("classpath:people.birthday");
            peopleBirthday = resource.getFile();
        } catch (IOException exception) {
            log.error("File doesn't exist.", exception);
        }
    }

    @PreDestroy
    public void destroy() {
    }

    @Override
    public List<Person> findPeoplesBornOn(LocalDate date) {
        try {
            return Files.lines(peopleBirthday.toPath()).map(line -> {
                String [] lineContent = line.split("\\s");
                return new Person(lineContent[0], createDate(lineContent[1]), lineContent[2]);
            }).filter(p -> p.getBirthday().equals(date)).collect(Collectors.toList());
        } catch (IOException exception) {
            log.error("Error parsing file.", exception);
        }
        return Collections.emptyList();
    }

    private LocalDate createDate(String dateTime){
        return LocalDate.parse(dateTime);
    }
}
