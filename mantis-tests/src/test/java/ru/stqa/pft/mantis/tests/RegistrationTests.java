package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by chesnokova.sa on 06.12.2016.
 */
public class RegistrationTests extends TestBase {

    @Test
    public void testRegistation() {
        TestBase.app.registration().start("user1", "test@test.com");

    }
}
