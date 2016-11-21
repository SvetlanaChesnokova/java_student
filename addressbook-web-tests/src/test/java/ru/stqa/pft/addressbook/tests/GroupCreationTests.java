package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    //тест для создания группы
    app.goTo().groupPage();
    //для сравнения размера списка до добавления записаи
    Groups before = app.group().all();
    GroupData group =new GroupData().withName ("test17");
    app.group().create(group);
    //для сравнения размера списка после собавления записаи
    Groups after = app.group().all();
    //проверка, сравнение
      assertThat(after.size() , equalTo(before.size()+1));

      //проверялка на совпадение 2-х элиментов - объектов
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    //app.goTo().gotoHomePage();
  }

}
