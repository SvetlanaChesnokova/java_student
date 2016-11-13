package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

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
    //int before = app.getClientHelper().getClientCount();
    List<ClientData> before = app.getClientHelper().getClientList();
    app.getClientHelper().initAddNewModification("2"); //before.size()-1
    app.getClientHelper().fillAddNewForm("Vasilievna", "Vasil", "", "KOL");
    app.getClientHelper().telephoneAddNewForm("452463", "257", "27872kl");
//GroupData group =new GroupData(before.get(before.size()-1).getId(),"test1", null, null);
    // ClientData contakt =  new ClientData("Petrova","Liza", "RF, P-T","8969631478", "@", "Liza@tre", "Petrova@erw.ru", "--", null);
    ClientData contakt =  new ClientData(before.get(before.size()-1).getId(),"Petrova","Liza", "RF, P-T","8969631478", "@", "Liza@tre", "Petrova@erw.ru", "--", null);
    app.getClientHelper().emllAddNewForm(contakt, false);
    app.getClientHelper().secondaryAddNewForm("P-T, Lenina 876", "987456321", "g");
    app.getClientHelper().ubdateAddNewCreation();
    app.getClientHelper().returnAddNewCreation();
    //подсчет кол-ва групп (строк) после добавления
    //int after = app.getClientHelper().getClientCount();
    List<ClientData> after = app.getClientHelper().getClientList();
    //проверка, сравнение
    //Assert.assertEquals(after , before);
    Assert.assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
   // Assert.assertEquals(before, after);

    //before.remove(before.size() - 1);
    before.remove(before.size()-1);
    before.add(contakt);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));


  }


}
