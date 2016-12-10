package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by chesnokova.sa on 09.12.2016.
 */
public class PasswordUserTest extends TestBase {
    @Test
    public void testPasswordUser() throws IOException, MessagingException {

       // assertTrue(wd.findElement(By.xpath(String.format("<span id=\"logged-in-user\">"))));
      /*  assertTrue(app.newSession().login(user, password));
      long now = System.currentTimeMillis();
        String email= String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        //чтобы работал тест, надо внести изменения в пути  конфигурационного файла C:\xampp\htdocs\mantisbt-1.3.4\config
        // и так тоже не работает
        TestBase.app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        //находим именно наше письмо
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));  */
    }

}
