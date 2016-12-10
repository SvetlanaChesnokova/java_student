package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by chesnokova.sa on 06.12.2016.
 */
public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
     public void testRegistration() throws IOException, MessagingException {
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
 //        assertTrue(app.newSession().login(user, password));
     }



    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        //ищим отправленное письмо, среди всех
         MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        //регулярное вырожение
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
         return regex.getText(mailMessage.text);


    }

    //(alwaysRun = true) - это чтобы останавливался даже при не успешном запуске
    @AfterMethod (alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
