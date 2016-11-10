package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //тест для создания группы
    app.getNavigationHelper().gotoGroupPage();
    //подсчет кол-ва групп (строк) до добавления
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    //подсчет кол-ва групп (строк) после добавления
    int after = app.getGroupHelper().getGroupCount();
    //проверка, сравнение
    Assert.assertEquals(after , before+1);
    app.getNavigationHelper().gotoHomePage();
  }

}
