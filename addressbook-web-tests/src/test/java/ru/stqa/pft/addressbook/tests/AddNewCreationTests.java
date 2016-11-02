package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class AddNewCreationTests extends TestBase {

    @Test
    public void testAddNewCreation() {
        //содержимое теста
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initAddNewCreation();
        app.getGroupHelper().fillAddNewForm("Nikolai", "Aleksandrovich","Sidorov", "Nikol", "5", "IT", "RF, NSK");
        app.getGroupHelper().telephoneAddNewForm("3-147-258", "+72589631478", "5347-852", "357-1598");
        app.getGroupHelper().emlAddNewForm("3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598");
        app.getGroupHelper().secondaryAddNewForm("Nsk, Lenina 7", "+72589631478", "");
        app.getGroupHelper().submitGroupCreation();
        //или можно сохранить изменения так:
        //app.getGroupHelper().submitAddNewCreation();
        app.getGroupHelper().returnAddNewCreation();

    }

}
