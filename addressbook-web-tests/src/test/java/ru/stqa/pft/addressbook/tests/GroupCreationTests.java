package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  //провайдер тестовых данных, для большого кол-ва выполняемых тестов
   @DataProvider
   public Iterator<Object[]> validGroups() {
       List<Object[]> list = new ArrayList<Object[]>();
       list.add(new Object[]{new GroupData().withName("test1").withHeader("header 1").withFooter("footer 1")});
       list.add(new Object[]{new GroupData().withName("test2").withHeader("header 2").withFooter("footer 2")});
       list.add(new Object[]{new GroupData().withName("test3").withHeader("header 3").withFooter("footer 3")});
        return list.iterator();
   }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {

    // GroupData group =new GroupData().withName ("test17");
    //тест для создания группы
    app.goTo().groupPage();
    //для сравнения размера списка до добавления записаи
    Groups before = app.group().all();
    app.group().create(group);
    //проверка, сравнение, по кол-ву.
    //Хеширование и предварительные проверки при использовании более быстрой операции  app.group().count()
    assertThat(app.group().count()  , equalTo(before.size()+1));
    //для сравнения размера списка после собавления записаи
    Groups after = app.group().all();


      //проверялка на совпадение 2-х элиментов - объектов
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }


    @Test
    public void testBadGroupCreation() {
        //тест для создания группы
        app.goTo().groupPage();
        //для сравнения размера списка до добавления записаи
        Groups before = app.group().all();
        GroupData group =new GroupData().withName ("test17это негативный тест ' - на запрет апострофа");
        app.group().create(group);
        //проверка, сравнение
        assertThat(app.group().count() , equalTo(before.size()));
        //для сравнения размера списка после собавления записаи
        Groups after = app.group().all();
       //проверялка на совпадение 2-х элиментов - объектов
        assertThat(after, equalTo(before));

    }

}
