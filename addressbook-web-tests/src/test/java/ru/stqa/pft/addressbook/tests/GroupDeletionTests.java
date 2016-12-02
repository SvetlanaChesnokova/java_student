package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {
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
  public void testGroupDeletion() {
    //тест для удаления группы
    //для сравнения размера списка до добавления записаи
    //Set - возвращает множество элементов
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);
      //проверка, сравнение
    //Хеширование и предварительные проверки при использовании более быстрой операции  app.group().count()
      assertThat(app.group().count()  , equalTo(before.size()-1));
    //для сравнения размера списка после удаления записаи  , загрузка данных
    Groups after = app.db().groups();

     //проверялка на совпадение 2-х элиментов - объектов
    assertThat(after, equalTo(before.withOut(deletedGroup)));

    //проверка в пользовательском интерфейсе / откльчаемый метод
    verifyGroupListUI();
  }


}
