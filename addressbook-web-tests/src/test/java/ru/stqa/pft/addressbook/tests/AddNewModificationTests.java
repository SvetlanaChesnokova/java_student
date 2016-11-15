package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Светлана on 02.11.2016.
 */
public class AddNewModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.contakt().initHome();
    app.goTo().gotoHomePage();
    //проверяем есть ли хоть одна запись для удаления
    if (app.contakt().list().size() == 0) {
      //если нет записи, то создаем ее
      app.contakt().create(new ClientData().withP_lastname("Sidorov").withP_firstnam("Nikolai").withP_address("RF, NSK")
              .withP_homepage("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
              .withP_email3("Sidorov@erw.ru"));
    }
  }

  @Test
  public void testAddNewModification() {
    //тест для модификации контакта
    //подсчет кол-ва строк до добавления
    List<ClientData> before = app.contakt().list();
    int index = before.size()-1;
    ClientData contakt =  new ClientData().withId(before.get(index).getId()).withP_lastname("Petrova").withP_firstnam("Liza")
            .withP_address("RF, P-T").withP_phones("8969631478").withP_email("Liza@tre").withP_email2("Petrova@erw.ru");
    app.contakt().modify(index, contakt,"Vasilievna", "Vasil", "", "KOL","452463", "257", "27872kl", "P-T, Lenina 876", "987456321", "g");
    //подсчет кол-ва групп (строк) после добавления
    List<ClientData> after = app.contakt().list();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

    before.remove(index);
    before.add(contakt);
    Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }




}
