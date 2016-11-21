package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Светлана on 03.11.2016.
 */
public class AddNewDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.contakt().initHome();
    app.goTo().gotoHomePage();
    //проверяем есть ли хоть одна запись для удаления
    if (app.contakt().all().size() == 0) {
      //если нет записи, то создаем ее
      app.contakt().create(new ClientData().withP_lastname("Sidorov").withP_firstnam("Nikolai").withP_address("RF, NSK")
              .withP_phones("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
              .withP_email3("Sidorov@erw.ru").withP_homepage("ttt").withGroup("test17"));
    }
  }


  @Test
  public void testAddNewDeletion(){
    //тест для удаления контакта
    //подсчет кол-ва строк до добавления
    Clients before = app.contakt().all();
    ClientData deletedClient = before.iterator().next();
    app.contakt().delete(deletedClient);

    //проверка, сравнение
    assertThat(app.contakt().count(), equalTo(before.size()-1));
    //подсчет кол-ва групп (строк) после добавления
    Clients after = app.contakt().all();

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    assertThat(after, equalTo(before.withOut(deletedClient)));


  }


}
