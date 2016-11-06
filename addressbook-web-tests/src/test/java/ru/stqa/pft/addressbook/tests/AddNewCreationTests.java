package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

public class AddNewCreationTests extends TestBase {

    @Test
    public void testAddNewCreation() {
        app.getClientHelper().initAddNewCreation();
        app.getClientHelper().fillAddNewForm("Nikolai", "Aleksandrovich","Sidorov", "Nikol", "5", "IT", "RF, NSK");
        app.getClientHelper().telephoneAddNewForm("3-147-258", "+72589631478", "5347-852", "357-1598");
        //app.getClientHelper().emlAddNewForm("3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598");
        app.getClientHelper().emllAddNewForm( new ClientData("3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1"), true);
        app.getClientHelper().secondaryAddNewForm("Nsk, Lenina 7", "+72589631478", "");
        app.getClientHelper().submitAddNewCreation();
        app.getClientHelper().returnAddNewCreation();

    }

}
