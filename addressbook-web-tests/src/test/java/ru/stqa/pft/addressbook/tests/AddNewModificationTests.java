package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    Set<ClientData> before = app.contakt().all();
   // int index = before.size()-1;
    ClientData modifClient = before.iterator().next();
    ClientData contakt =  new ClientData().withId(modifClient.getId()).withP_lastname("Petrova").withP_firstnam("Liza")
            .withP_address("RF, P-T").withP_phones("8969631478").withP_email("Liza@tre");//.withP_email2("Petrova@erw.ru");

    ClientData dop_fill =  new ClientData().withP_middlename("Vasilievna").withP_nickname("Vasil").withP_title("ttt")
            .withP_company("KOL");
    ClientData dop_telephone =  new ClientData().withP_home("452463").withP_work("257").withP_fax("27872kl");
    ClientData dop_secondary=  new ClientData().withP_address2("P-T, Lenina 876").withP_phone2("987456321").withP_notes("****7g");

    app.contakt().modify(contakt, dop_fill, dop_telephone, dop_secondary);
    //подсчет кол-ва групп (строк) после добавления
    Set<ClientData> after = app.contakt().all();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

    before.remove(modifClient);
    before.add(contakt);
   /* Comparator<? super ClientData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId); */
    Assert.assertEquals(before, after);

  }




}
