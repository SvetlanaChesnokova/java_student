package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.List;

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
    if (app.contakt().list().size() == 0) {
      //если нет записи, то создаем ее
      app.contakt().create(new ClientData().withP_lastname("Sidorov").withP_firstnam("Nikolai").withP_address("RF, NSK")
              .withP_phones("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
              .withP_email3("Sidorov@erw.ru").withP_homepage("ttt"));
    }
  }


  @Test
  public void testAddNewDeletion(){
    //тест для удаления контакта

    //подсчет кол-ва строк до добавления
    List<ClientData> before = app.contakt().list();
    int index = before.size()-1;
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1
    app.contakt().delete(index);
    //явное ожидание, главной страницы
    //подсчет кол-ва групп (строк) после добавления
    List<ClientData> after = app.contakt().list();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size()-1);

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    before.remove(index);
    Assert.assertEquals(before, after);



  }


}
