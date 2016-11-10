package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

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
    int before = app.getClientHelper().getClientCount();
    app.getClientHelper().selectAddNew();
    app.getClientHelper().initAddNewDelete();
    app.getClientHelper().initAddNewAlert();
    //подсчет кол-ва групп (строк) после добавления
    int after = app.getClientHelper().getClientCount();
    //проверка, сравнение
    Assert.assertEquals(after , before-1);

  }
}
