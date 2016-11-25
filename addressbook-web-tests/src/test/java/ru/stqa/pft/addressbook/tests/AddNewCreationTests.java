package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewCreationTests extends TestBase {

    @Test
    public void testAddNewCreation() {
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        Clients before = app.contakt().all();
        File photo = new File("src/test/resources/image.png");
        ClientData contakt =  new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai")
                .withP_address("RF, NSK").withP_homepage("+72589631478").withP_email("3-147-258@")
                .withP_email2("Nikolai@tre").withP_email3("Sidorov@erw.ru").withP_phones("357-1598")
                .withGroup("test17").withP_home("741 85").withP_work("858(41) 4757").withPhoto(photo);
        app.contakt().create(contakt);
        //проверка, сравнение
        assertThat(app.contakt().count(), equalTo(before.size()+1));
        //подсчет кол-ва групп (строк) после добавления
        Clients after = app.contakt().all();

        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
        assertThat(after, equalTo(before.withAdded(
                contakt.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    //отключаем тест (enabled = false)
    @Test (enabled = false)
    public void testBadAddNewCreation() {
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        Clients before = app.contakt().all();
        ClientData contakt =  new ClientData().withP_firstnam("Sidorov8 это негативный тест ' - на запрет апострофа").withP_lastname("Nikolai")
                .withP_address("RF, NSK").withP_homepage("+72589631478").withGroup("test17");
        app.contakt().create(contakt);
        //проверка, сравнение
        assertThat(app.contakt().count(), equalTo(before.size()));
       //подсчет кол-ва групп (строк) после добавления
        Clients after = app.contakt().all();
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
      //у меня берется другой путь: C:\data\tests\src\test\resources\image.png

    }

}
