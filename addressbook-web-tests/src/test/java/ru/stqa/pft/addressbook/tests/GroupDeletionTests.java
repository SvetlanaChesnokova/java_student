package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (! app.getGroupHelper().isThereAGroup()) {
      //если нет записи, то создаем ее
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
  //  app.getGroupHelper().selectp(); //делала для себя, чтобы проверить ожидание
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
