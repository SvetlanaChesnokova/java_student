package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //тест для создания группы
    app.getNavigationHelper().gotoGroupPage();
    //для сравнения размера списка до добавления записаи
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group =new GroupData("test177", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    //для сравнения размера списка после собавления записаи
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size()+1);

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

    before.add(group);
    //список переводим в поток, далее сравниваем и находим мах элемент по id
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before , after);

    app.getNavigationHelper().gotoHomePage();
  }

}
