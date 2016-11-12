package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.List;

/**
 * Created by Светлана on 03.11.2016.
 */
public class AddNewDeletionTests extends TestBase {

  @Test
  public void testAddNewDeletion(){
    //тест для удаления контакта
    app.getClientHelper().initAddNewHome();
    app.getNavigationHelper().gotoHomePage();
    //проверяем есть ли хоть одна запись для удаления
    if (! app.getClientHelper().isThereAClient()) {
      //если нет записи, то создаем ее
      app.getClientHelper().createClient(new ClientData("Sidorov","Nikolai", "RF, NSK","+72589631478","3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1"));
    }
    //подсчет кол-ва строк до добавления
    //int before = app.getClientHelper().getClientCount();
    List<ClientData> before = app.getClientHelper().getClientList();
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1
    app.getClientHelper().selectAddNew(0);
    app.getClientHelper().initAddNewDelete();
    app.getClientHelper().initAddNewAlert();
    //явное ожидание, главной страницы
    //app.getClientHelper().selectp();
    //подсчет кол-ва групп (строк) после добавления
    //int after = app.getClientHelper().getClientCount();
    List<ClientData> after = app.getClientHelper().getClientList();
    //проверка, сравнение
    //Assert.assertEquals(after , before-1);
    Assert.assertEquals(after.size() , before.size()-1);

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);



  }
}
