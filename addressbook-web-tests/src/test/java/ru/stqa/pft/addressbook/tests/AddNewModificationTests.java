package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

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
    //проверяем есть ли хоть одна запись для модификации
    if (app.db().clients().size() == 0) {
      //если нет записи, то создаем ее
      app.contakt().create(new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai").withP_address("RF, NSK")
              .withP_homepage("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
              .withP_email3("Sidorov@erw.ru").withP_phones("357-1598").withGroup("test17")
              .withP_home("741 85").withP_work("858(41) 4757"));
    }
  }

  @Test
  public void testAddNewModification() {
    //тест для модификации контакта
    //подсчет кол-ва строк до добавления
    Clients before = app.db().clients();
    ClientData modifClient = before.iterator().next();
    ClientData contakt =  new ClientData().withId(modifClient.getId()).withP_lastname("Igorevna96").withP_firstnam("Liza")
            .withP_address("RF, P-T xmxmxm jzjzj52857 , yystr").withP_phones("896-963-1478").withP_email("Liza@tre").withP_email2("Petrova@erw.ru")
            .withP_middlename("Vasilievna69").withP_nickname("Gosh").withP_title("ttt").withP_company("KOL")
            .withP_home("4(524)6396").withP_work("257").withP_fax("27872kl").withP_address2("96P-T, Lenina 876")
            .withP_email3("fhhj5632@gj.jg").withP_notes("****7g");

    app.contakt().modify(contakt);
    //проверка, сравнение
    assertThat(app.contakt().count(), equalTo(before.size()));
    //подсчет кол-ва групп (строк) после добавления
    Clients after = app.db().clients();

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    assertThat(after, equalTo(before.withOut(modifClient).withAdded(contakt)));

  }




}
