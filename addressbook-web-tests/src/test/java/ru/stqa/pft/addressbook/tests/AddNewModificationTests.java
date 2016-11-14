package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Светлана on 02.11.2016.
 */
public class AddNewModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.getClientHelper().initAddNewHome();
    app.getNavigationHelper().gotoHomePage();
    //проверяем есть ли хоть одна запись для удаления
    if (! app.getClientHelper().isThereAClient()) {
      //если нет записи, то создаем ее
      app.getClientHelper().createClient(new ClientData("Sidorov","Nikolai", "RF, NSK","+72589631478","3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1"));
    }
  }

  @Test
  public void testAddNewModification() {
    //тест для модификации контакта

    //подсчет кол-ва строк до добавления
    //int before = app.getClientHelper().getClientCount();
    List<ClientData> before = app.getClientHelper().getClientList();
    int index = before.size()-1;
    ClientData contakt =  new ClientData(before.get(index).getId(),"Petrova","Liza", "RF, P-T","8969631478", "@", "Liza@tre", "Petrova@erw.ru", "--", null);
    app.getClientHelper().modifyContact(index, contakt,"Vasilievna", "Vasil", "", "KOL","452463", "257", "27872kl", "P-T, Lenina 876", "987456321", "g");
    //подсчет кол-ва групп (строк) после добавления
    //int after = app.getClientHelper().getClientCount();
    List<ClientData> after = app.getClientHelper().getClientList();
    //проверка, сравнение
    //Assert.assertEquals(after , before);
    Assert.assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

    before.remove(index);
    before.add(contakt);
    Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
   // Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));

  }




}
