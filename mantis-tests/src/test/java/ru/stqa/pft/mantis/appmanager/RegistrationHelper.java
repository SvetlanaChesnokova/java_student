package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by chesnokova.sa on 06.12.2016.
 */
public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        //открываем страницу "зарегистрировать новую учетную запись"
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"),username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));     //Signup
    }

    public void finish(String confirmationLink, String password) {
         wd.get(confirmationLink);
         type(By.name("password"), password);
         type(By.name("password_confirm"), password);
         click(By.cssSelector("input[value='Изменить учетную запись']"));        //Update_User
    }
}
