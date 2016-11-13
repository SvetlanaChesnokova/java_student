package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //тест для создания группы
    app.getNavigationHelper().gotoGroupPage();
    //подсчет кол-ва групп (строк) до добавления
  //  int before = app.getGroupHelper().getGroupCount();
    //для сравнения размера списка до собавления записаи
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group =new GroupData("test1", "test2", "test3");
    //GroupData group =new GroupData(before.get(before.size()-1).getId(),"test1", "test2", "test3");
    app.getGroupHelper().createGroup(group);
    //подсчет кол-ва групп (строк) после добавления
    //int after = app.getGroupHelper().getGroupCount();
    //для сравнения размера списка после собавления записаи
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //проверка, сравнение
   // Assert.assertEquals(after , before+1);
    Assert.assertEquals(after.size() , before.size()+1);

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

    int max =0;
    for (GroupData g : after){
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));

    app.getNavigationHelper().gotoHomePage();
  }

}
