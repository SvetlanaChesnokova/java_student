package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
    if (app.contakt().all().size() == 0) {
      //если нет записи, то создаем ее
      app.contakt().create(new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai").withP_address("RF, NSK")
              .withP_homepage("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
              .withP_email3("Sidorov@erw.ru").withP_phones("357-1598").withGroup("test17"));


    }
  }

  @Test
  public void testAddNewModification() {
    //тест для модификации контакта
    //подсчет кол-ва строк до добавления


    Clients before = app.contakt().all();
    ClientData modifClient = before.iterator().next(); ///****
    ClientData contakt =  new ClientData().withId(modifClient.getId()).withP_lastname("Igorevna96").withP_firstnam("Liza")
            .withP_address("RF, P-T").withP_phones("8969631478").withP_email("Liza@tre");//.withP_email2("Petrova@erw.ru");

    ClientData dop_fill =  new ClientData().withP_middlename("Vasilievna69").withP_nickname("Gosh").withP_title("ttt")
            .withP_company("KOL");
    ClientData dop_telephone =  new ClientData().withP_home("45246396").withP_work("257").withP_fax("27872kl");
    ClientData dop_secondary=  new ClientData().withP_address2("96P-T, Lenina 876").withP_phone2("987456321").withP_notes("****7g");

    app.contakt().modify(contakt, dop_fill, dop_telephone, dop_secondary);
    //подсчет кол-ва групп (строк) после добавления
    Clients after = app.contakt().all();
    //проверка, сравнение
    assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

  /*  before.remove(modifClient);
    before.add(contakt);
    contakt.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    contakt.withId(before.stream().mapToInt((c) -> c.getId()).max().getAsInt());
   */
   /* Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId); */


    //не работает это сравнение корректно , что-то не так сортирую
   // assertEquals(before, after);

    assertThat(after, equalTo(before.withOut(modifClient).withAdded(contakt)));

  }




}
