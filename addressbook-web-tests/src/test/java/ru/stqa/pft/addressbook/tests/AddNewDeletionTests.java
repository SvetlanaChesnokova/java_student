package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Светлана on 03.11.2016.
 */
public class AddNewDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    //проверяем есть ли хоть одна запись для удаления
    if (app.db().clients().size() == 0) {
      //если нет записи, то создаем ее

      //проверяем есть ли хоть группа, для возможности выбрать ее в контакте
      if (app.db().groups().size() == 0) {
        //если нет записи, то создаем ее
        //вынесена, подготовка теста
        app.goTo().groupPage();
        //если нет записи, то создаем ее
        app.group().create( new GroupData().withName("test3"));
      }
      Groups groups = app.db().groups();
      app.contakt().initHome();
      //переход на нужную форму
      app.goTo().gotoHomePage();
      app.contakt().create(new ClientData().withP_lastname("Sidorov").withP_firstnam("Nikolai").withP_address("RF, NSK")
              .withP_phones("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
              .withP_email3("Sidorov@erw.ru").withP_homepage("ttt").inGroup(groups.iterator().next())
              .withP_home("741 85").withP_work("858(41) 4757"));
    }
  }


  @Test
  public void testAddNewDeletion(){
    //тест для удаления контакта
    //подсчет кол-ва строк до добавления
    Clients before = app.db().clients();
    ClientData deletedClient = before.iterator().next();
    app.contakt().delete(deletedClient);

    //проверка, сравнение
    assertThat(app.contakt().count(), equalTo(before.size()-1));
    //подсчет кол-ва групп (строк) после добавления
    Clients after = app.db().clients();

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    assertThat(after, equalTo(before.withOut(deletedClient)));

    //проверка в пользовательском интерфейсе / откльчаемый метод
    verifyClientListUI();
  }


}
