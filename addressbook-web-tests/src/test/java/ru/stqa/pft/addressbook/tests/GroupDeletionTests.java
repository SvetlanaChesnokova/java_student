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

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.goTo().groupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (app.group().all().size() == 0) {
      //если нет записи, то создаем ее
      app.group().create( new GroupData().withName("test1").withFooter("tt"));
    }
  }

  @Test
  public void testGroupDeletion() {
    //тест для удаления группы

    //для сравнения размера списка до добавления записаи
    //Set - возвращает множество элементов
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    //для сравнения размера списка после собавления записаи
    Groups after = app.group().all();
    //проверка, сравнение
    assertEquals(after.size() , before.size()-1);
      //проверялка на совпадение 2-х элиментов - объектов
    assertThat(after, equalTo(before.withOut(deletedGroup)));



  }




}
