package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Светлана on 02.11.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.goTo().groupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (app.group().list().size() == 0) {
      //если нет записи, то создаем ее
      app.group().create( new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupModification(){
    //тест для модификации группы

    //для сравнения размера списка до собавления записаи
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData group =new GroupData().withId(before.get(index).getId()).withName("test1")
            .withFooter(null).withHeader("abc");
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1
    app.group().modify(index, group);
    //для сравнения размера списка после собавления записаи
    List<GroupData> after = app.group().list();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before , after);

  }


}
