package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by chesnokova.sa on 06.12.2016.
 */
public class RegistrationHelper {
    private final ApplicationManager app;
    //для блаузера драйвер
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        //открываем страницу "зарегистрировать новую учетную запись"
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
