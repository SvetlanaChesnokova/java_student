package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Светлана on 02.11.2016.
 */
public class AddNewModificationTests extends TestBase {

  @Test
  public void testAddNewModification() {
    app.getClientHelper().initAddNewHome();
    app.getClientHelper().selectAddNew();
    app.getClientHelper().initAddNewModification();
    app.getClientHelper().fillAddNewForm("Liza", "Vasilievna","Petrova", "Vasil", "", "KOL", "RF, P-T");
    app.getClientHelper().telephoneAddNewForm("452463", "8969631478", "257", "27872kl");
    app.getClientHelper().emlAddNewForm("@", "Liza@tre", "Petrova@erw.ru", "--");
    app.getClientHelper().secondaryAddNewForm("P-T, Lenina 876", "987456321", "g");
    app.getClientHelper().ubdateAddNewCreation();
    app.getClientHelper().returnAddNewCreation();

  }


}
