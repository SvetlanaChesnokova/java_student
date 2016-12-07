package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by chesnokova.sa on 06.12.2016.
 */
public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistation() {
        TestBase.app.registration().start("user1", "user1@localhost.localdomain");
    }

    //(alwaysRun = true) - это чтобы останавливался даже при не успешном запуске
    @AfterMethod (alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
