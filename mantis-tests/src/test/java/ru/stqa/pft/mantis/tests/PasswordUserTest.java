package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by chesnokova.sa on 09.12.2016.
 */
public class PasswordUserTest extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testPasswordUser() throws IOException, MessagingException {
      //  TestBase.app.session().login("administrator", "root");
        TestBase.app.session().login("administrator", "root");
       // assertTrue(app.findElement(By.xpath(String.format("<span id=\"logged-in-user\">"))));
       // Assert.assertTrue(app.newSession().login(app.findElement(By.xpath(String.format("<span id=\"logged-in-user\">"))).text, "administrator"));

        //String email= (By.xpath("//div[@id='manage-user-div']/table/tbody/tr[5]/td[3]");
        String password = "password";
        UserData userBD = app.session().selectUser();

        TestBase.app.session().resetPassword(userBD.getId());
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        //находим именно наше письмо
        String confirmationLink = findConfirmationLink(mailMessages, userBD.getEmail());
        //Считывание текста из письма и пароля
        app.registration().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(userBD.getName(), password));
       // assertTrue(wd.findElement(By.xpath(String.format("<span id=\"logged-in-user\">"))));
      /*
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));  */
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        //ищим отправленное письмо, среди всех
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        //регулярное вырожение
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    //(alwaysRun = true) - это чтобы останавливался даже при не успешном запуске
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
