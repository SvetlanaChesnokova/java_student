package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Светлана on 02.11.2016.
 */
public class AddNewModificationTests extends TestBase {

  @Test
  public void testAddNewModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initAddNewHome();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initAddNewModification();
    app.getGroupHelper().fillAddNewForm("Liza", "Vasilievna","Petrova", "Vasil", "", "KOL", "RF, P-T");
    app.getGroupHelper().telephoneAddNewForm("452463", "8969631478", "257", "27872kl");
    app.getGroupHelper().emlAddNewForm("@", "Liza@tre", "Petrova@erw.ru", "--");
    app.getGroupHelper().secondaryAddNewForm("P-T, Lenina 876", "987456321", "g");
    app.getGroupHelper().ubdateAddNewCreation();
    app.getGroupHelper().returnAddNewCreation();

  }


}
