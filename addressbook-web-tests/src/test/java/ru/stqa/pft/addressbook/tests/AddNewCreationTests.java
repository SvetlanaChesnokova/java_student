package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //вынесена, подготовка теста
        //проверяем есть ли хоть группа, для возможности выбрать ее в контакте
        if (app.db().groups().size() == 0) {
            //если нет записи, то создаем ее
                //вынесена, подготовка теста
                app.goTo().groupPage();
                //если нет записи, то создаем ее
                app.group().create( new GroupData().withName("test3"));
            }

    }

    //провайдер тестовых данных, для большого кол-ва выполняемых тестов
    @DataProvider
    public Iterator<Object[]> validContaktsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contakts.xml")))) {
            String xml = "";
            //читает и возвращает строку
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ClientData.class);
            List<ClientData> groups = (List<ClientData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @DataProvider
    public Iterator<Object[]> validContaktsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contakts.json")))) {
            String json = "";
            //читает и возвращает строку
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ClientData> groups = gson.fromJson(json, new TypeToken<List<ClientData>>() {
            }.getType());
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validContaktsFromJson")
    public void testAddNewCreation(ClientData contakt) {
        Groups groups = app.db().groups();
        //тест для создания контакта, с использованием тестовых данных из указанного файла
        //подсчет кол-ва строк до добавления
        Clients before = app.db().clients();
        app.contakt().create(contakt);
        //проверка, сравнение
        assertThat(app.contakt().count(), equalTo(before.size()+1));
        //подсчет кол-ва групп (строк) после добавления
        Clients after = app.db().clients();

        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
        assertThat(after, equalTo(before.withAdded(
                contakt.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

        //проверка в пользовательском интерфейсе / откльчаемый метод
        verifyClientListUI();
    }


    @Test (enabled = false)
    public void testAddNewCreation() {
        Groups groups = app.db().groups();
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        Clients before = app.db().clients();
        File photo = new File("src/test/resources/image.png");
        ClientData contakt =  new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai")
                .withP_address("RF, NSK").withP_homepage("+72589631478").withP_email("3-147-258@")
                .withP_email2("Nikolai@tre").withP_email3("Sidorov@erw.ru").withP_phones("357-1598")
                .withP_home("741 85").withP_work("858(41) 4757")
                .inGroup(groups.iterator().next());//.withPhoto(photo);
        //.inGroup(groups.iterator().next()) - таким образом выбираем группу, но если нет группы то ее надо создать
        app.contakt().create(contakt);
        //проверка, сравнение
        assertThat(app.contakt().count(), equalTo(before.size()+1));
        //подсчет кол-ва групп (строк) после добавления
        Clients after = app.db().clients();

        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
        assertThat(after, equalTo(before.withAdded(
                contakt.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    //отключаем тест (enabled = false)
    @Test (enabled = false)
    public void testBadAddNewCreation() {
        Groups groups = app.db().groups();
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        Clients before = app.db().clients();
        ClientData contakt =  new ClientData().withP_firstnam("Sidorov8 это негативный тест ' - на запрет апострофа").withP_lastname("Nikolai")
                .withP_address("RF, NSK").withP_homepage("+72589631478").inGroup(groups.iterator().next());
        app.contakt().create(contakt);
        //проверка, сравнение
        assertThat(app.contakt().count(), equalTo(before.size()));
       //подсчет кол-ва групп (строк) после добавления
        Clients after = app.db().clients();
        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
        assertThat(after, equalTo(before));
    }

    @Test     (enabled = false)
    public void testCurrentDir() {
      File currentDir = new File(".");
      System.out.println("**************************");
      System.out.println(currentDir.getAbsolutePath());
      System.out.println("**************************");
      File photo = new File("src/test/resources/image.png");
      System.out.println(photo.getAbsolutePath());
      System.out.println(photo.exists());
    }

}
