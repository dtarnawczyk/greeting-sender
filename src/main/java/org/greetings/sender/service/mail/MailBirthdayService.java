package org.greetings.sender.service.mail;

import org.greetings.sender.repository.Person;
import org.greetings.sender.service.SendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailBirthdayService implements SendService{

    private static final Logger log = LoggerFactory.getLogger(MailBirthdayService.class);

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailBirthdayService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendGreetings(List<Person> receivers) {
        receivers.stream().forEach(person -> {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(person.getEmail());
            mailMessage.setReplyTo("someone@localhost");
            mailMessage.setFrom("someone@localhost");
            mailMessage.setSubject("Lorem ipsum "+ person.getName());
            mailMessage.setText("Lorem ipsum dolor sit amet " + person.getName());
            javaMailSender.send(mailMessage);
        });
    }
}
