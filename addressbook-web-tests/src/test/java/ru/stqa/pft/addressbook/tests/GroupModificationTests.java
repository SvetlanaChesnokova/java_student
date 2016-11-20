package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Светлана on 02.11.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.goTo().groupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (app.group().all().size() == 0) {
      //если нет записи, то создаем ее
      app.group().create( new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupModification(){
    //тест для модификации группы

    //для сравнения размера списка до собавления записаи
    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group =new GroupData().withId(modifiedGroup.getId()).withName("test1")
            .withFooter("test2").withHeader("abc");
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1
    app.group().modify(group);
    //для сравнения размера списка после собавления записаи
    Groups after = app.group().all();
    //проверка, сравнение
    assertEquals(after.size() , before.size());

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

  }


}
