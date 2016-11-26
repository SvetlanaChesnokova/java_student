package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  //провайдер тестовых данных, для большого кол-ва выполняемых тестов
   @DataProvider
   public Iterator<Object[]> validGroups() throws IOException {
       List<Object[]> list = new ArrayList<Object[]>();
       BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
       String xml = "";
       //читает и возвращает строку
       String line = reader.readLine();
       while (line != null) {
           xml += line;
           line = reader.readLine();
       }
       XStream xstream = new XStream();
       xstream.processAnnotations(GroupData.class);
       List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
       return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
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
