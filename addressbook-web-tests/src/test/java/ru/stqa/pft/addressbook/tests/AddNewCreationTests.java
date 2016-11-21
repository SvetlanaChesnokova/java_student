package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddNewCreationTests extends TestBase {

    @Test
    public void testAddNewCreation() {
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        Clients before = app.contakt().all();
        ClientData contakt =  new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai")
                .withP_address("RF, NSK").withP_homepage("+72589631478").withP_email("3-147-258@")
                .withP_email2("Nikolai@tre").withP_email3("Sidorov@erw.ru").withP_phones("357-1598")
                .withGroup("test17");
        app.contakt().create(contakt);

        //подсчет кол-ва групп (строк) после добавления
        Clients after = app.contakt().all();
        //проверка, сравнение
        assertEquals(after.size(), before.size() + 1);

        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData
        assertThat(after, equalTo(before.withAdded(
                contakt.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
