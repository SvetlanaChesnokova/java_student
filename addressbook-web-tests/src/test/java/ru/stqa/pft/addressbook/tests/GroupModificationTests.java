package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Светлана on 02.11.2016.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    //тест для модификации группы
    app.getNavigationHelper().gotoGroupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (! app.getGroupHelper().isThereAGroup()) {
      //если нет записи, то создаем ее
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    //подсчет кол-ва групп (строк) до добавления
    int before = app.getGroupHelper().getGroupCount();
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1", null, null));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    //подсчет кол-ва групп (строк) после добавления
    int after = app.getGroupHelper().getGroupCount();
    //проверка, сравнение
    Assert.assertEquals(after , before);

  }
}
