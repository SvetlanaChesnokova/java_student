package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
   public Iterator<Object[]> validGroupsFromXml() throws IOException {
      try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
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
          return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
      }
   }


    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
            String json = "";
            //читает и возвращает строку
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    // тут мы указываем какой провайдер использовать(dataProvider = "validGroupsFromJson") - для файлов *.Json
    //(dataProvider = "validGroupsFromXml") - - для файлов *.Xml
    @Test(dataProvider = "validGroupsFromJson")
  public void testGroupCreation(GroupData group) {
    //тест для создания группы, с использованием тестовых данных из указанного файла
    app.goTo().groupPage();
    //для сравнения размера списка до добавления записаи
    Groups before = app.db().groups();
    app.group().create(group);
    //проверка, сравнение, по кол-ву.
    //Хеширование и предварительные проверки при использовании более быстрой операции  app.group().count()
    assertThat(app.group().count()  , equalTo(before.size()+1));
    //для сравнения размера списка после собавления записаи
    Groups after = app.db().groups();
      //проверялка на совпадение 2-х элиментов - объектов
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }


    @Test
    public void testBadGroupCreation() {
        //тест для создания группы
        app.goTo().groupPage();
        //для сравнения размера списка до добавления записаи
        Groups before = app.db().groups();
        GroupData group =new GroupData().withName ("test17это негативный тест ' - на запрет апострофа")
                .withFooter("f").withHeader("v");
        app.group().create(group);
        //проверка, сравнение
        assertThat(app.group().count() , equalTo(before.size()));
        //для сравнения размера списка после собавления записаи
        Groups after = app.db().groups();
       //проверялка на совпадение 2-х элиментов - объектов
        assertThat(after, equalTo(before));

    }

}
