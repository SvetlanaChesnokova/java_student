package ru.stqa.pft.mantis.tests;


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
public class RegistrationVirtialPochtaTests extends TestBase {

    @Test
     public void testRegistration() throws IOException, MessagingException, com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException {
        long now = System.currentTimeMillis();
        String email= String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.james().createUser(user, password);
        TestBase.app.registration().start(user, email);
        //внешний почтовый сервер используем
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);

        //находим именно наше письмо
         String confirmationLink = findConfirmationLink(mailMessages, email);
        //Считывание текста из письма и пароля
         app.registration().finish(confirmationLink, password);
         assertTrue(app.newSession().login(user, password));
     }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        //ищим отправленное письмо, среди всех
         MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        //регулярное вырожение
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
         return regex.getText(mailMessage.text);
    }


}
