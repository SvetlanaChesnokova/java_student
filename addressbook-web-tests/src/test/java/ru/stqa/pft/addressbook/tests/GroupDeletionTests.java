package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    //тест для удаления группы
    app.getNavigationHelper().gotoGroupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (! app.getGroupHelper().isThereAGroup()) {
      //если нет записи, то создаем ее
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    //подсчет кол-ва групп (строк) до добавления
    int before = app.getGroupHelper().getGroupCount();
  //  app.getGroupHelper().selectp(); //делала для себя, чтобы проверить ожидание
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1, для удаления
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //подсчет кол-ва групп (строк) после добавления
    int after = app.getGroupHelper().getGroupCount();
    //проверка, сравнение
    Assert.assertEquals(after , before-1);
  }


}
