package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Светлана on 02.11.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.getNavigationHelper().gotoGroupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (! app.getGroupHelper().isThereAGroup()) {
      //если нет записи, то создаем ее
      app.getGroupHelper().createGroup( new GroupData("test1", "test2", "test3"));
    }
  }

  @Test
  public void testGroupModification(){
    //тест для модификации группы

    //подсчет кол-ва групп (строк) до добавления
   // int before = app.getGroupHelper().getGroupCount();
    //для сравнения размера списка до собавления записаи
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-1;
    GroupData group =new GroupData(before.get(index).getId(),"test1", null, null);
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1
    app.getGroupHelper().modifyGroup(index, group);
    //подсчет кол-ва групп (строк) после добавления
    //int after = app.getGroupHelper().getGroupCount();
    //для сравнения размера списка после собавления записаи
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //проверка, сравнение
    //Assert.assertEquals(after , before);
    Assert.assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before , after);
   // Assert.assertEquals(new HashSet<Object>(before) , new HashSet<Object>(after));

  }


}
