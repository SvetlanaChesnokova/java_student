package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    //вынесена, подготовка теста
    app.goTo().groupPage();
    //проверяем есть ли хоть одна запись для удаления
    if (app.group().list().size() == 0) {
      //если нет записи, то создаем ее
      app.group().create( new GroupData().withName("test1").withFooter("tt"));
    }
  }

  @Test
  public void testGroupDeletion() {
    //тест для удаления группы

    //для сравнения размера списка до обавления записаи
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    //before-1 - выбор последней строки, можно указать любую с 0 по before-1, для удаления
    app.group().delete(index);
    //для сравнения размера списка после собавления записаи
    List<GroupData> after = app.group().list();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size()-1);

    before.remove(index);
    Assert.assertEquals(before, after);


  }




}
