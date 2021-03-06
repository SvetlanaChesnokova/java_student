package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Светлана on 02.11.2016.
 */
public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size()==0) {
      //вынесена, подготовка теста
    app.goTo().groupPage();
      //если нет записи, то создаем ее
    app.group().create( new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupModification(){
    //тест для модификации группы
    //для сравнения размера списка до собавления записаи
    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group =new GroupData().withId(modifiedGroup.getId()).withName("test1")
            .withFooter("test2").withHeader("abc");
    app.goTo().groupPage();
    app.group().modify(group);
      //проверка, сравнение
     //Хеширование и предварительные проверки при использовании более быстрой операции  app.group().count()
      assertThat(app.group().count()  , equalTo(before.size()));
    //для сравнения размера списка после добавления записаи, загрузка данных
    Groups after = app.db().groups();

    //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

    //проверка в пользовательском интерфейсе / откльчаемый метод
    verifyGroupListUI();
  }




}
