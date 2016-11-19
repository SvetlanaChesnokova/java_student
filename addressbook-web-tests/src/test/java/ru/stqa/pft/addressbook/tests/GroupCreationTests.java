package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //тест для создания группы
    app.goTo().groupPage();
    //для сравнения размера списка до добавления записаи
    Set<GroupData> before = app.group().all();
    GroupData group =new GroupData().withName ("test17");
    app.group().create(group);
    //для сравнения размера списка после собавления записаи
    Set<GroupData> after = app.group().all();
    //проверка, сравнение
    Assert.assertEquals(after.size() , before.size()+1);

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before , after);

    //app.goTo().gotoHomePage();
  }

}
