package org.greetings.sender;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.greetings.sender.app.Application;
import org.greetings.sender.repository.Person;
import org.greetings.sender.repository.file.FilePeopleRepository;
import org.greetings.sender.service.SendService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class IntegrationTest {

    private GreenMail smtpServer;

    @Autowired
    private SendService sendService;

    @MockBean
    private FilePeopleRepository repository;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }

    @Test
    public void shouldSendMail() throws Exception {
        //given
        LocalDate dateOfSending = LocalDate.parse("2017-05-09");
        String username = "marian";
        String email = "marian@gmail.com";
        LocalDate dateOfBirth = LocalDate.parse("2017-05-09");
        Person person = new Person(username, dateOfBirth, email);
        //when
        when(repository.findPeoplesBornOn(dateOfSending)).thenReturn(Arrays.asList(person));
        sendService.sendGreetings(Arrays.asList(person));
        //then
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        assertTrue(content.contains("Lorem ipsum dolor sit amet " + person.getName()));
    }

}
