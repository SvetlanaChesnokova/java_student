package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

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
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    //для сравнения размера списка после собавления записаи
    Set<GroupData> after = app.group().all();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size()-1);

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);


  }




}
