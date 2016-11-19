package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.HashSet;
import java.util.List;

public class AddNewCreationTests extends TestBase {

    @Test
    public void testAddNewCreation() {
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        List<ClientData> before = app.contakt().list();
        ClientData contakt =  new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai").withP_address("RF, NSK")
                .withP_homepage("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
                .withP_email3("Sidorov@erw.ru").withP_phones("357-1598").withGroup("test1");
        app.contakt().create(contakt);
        //подсчет кол-ва групп (строк) после добавления
        List<ClientData> after = app.contakt().list();
        //проверка, сравнение
        Assert.assertEquals(after.size(), before.size() + 1);

        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

        contakt.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contakt);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
