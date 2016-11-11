package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

/**
 * Created by Светлана on 02.11.2016.
 */
public class AddNewModificationTests extends TestBase {

  @Test
  public void testAddNewModification() {
    //тест для модификации контакта
    app.getClientHelper().initAddNewHome();
    if (! app.getClientHelper().isThereAClient()) {
      //если нет записи, то создаем ее
      app.getClientHelper().createClient(new ClientData("Sidorov","Nikolai", "RF, NSK","+72589631478", "3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1"));
    }
    //подсчет кол-ва строк до добавления
    int before = app.getClientHelper().getClientCount();
    app.getClientHelper().initAddNewModification("2"); //как передать before-1 или преобразовать в str?
    app.getClientHelper().fillAddNewForm("Vasilievna", "Vasil", "", "KOL");
    app.getClientHelper().telephoneAddNewForm("452463", "257", "27872kl");
    app.getClientHelper().emllAddNewForm( new ClientData("Petrova","Liza", "RF, P-T","8969631478", "@", "Liza@tre", "Petrova@erw.ru", "--", null), false);
    app.getClientHelper().secondaryAddNewForm("P-T, Lenina 876", "987456321", "g");
    app.getClientHelper().ubdateAddNewCreation();
    app.getClientHelper().returnAddNewCreation();
    //подсчет кол-ва групп (строк) после добавления
    int after = app.getClientHelper().getClientCount();
    //проверка, сравнение
    Assert.assertEquals(after , before);

  }


}
