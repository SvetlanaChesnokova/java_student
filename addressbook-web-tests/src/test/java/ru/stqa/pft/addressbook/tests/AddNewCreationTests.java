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
        //int before = app.getClientHelper().getClientCount();
        List<ClientData> before = app.getClientHelper().getClientList();
        ClientData contakt =  new ClientData("Sidorov8", "Nikolai", "RF, NSK", "+72589631478", "3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1");
        //app.getClientHelper().createClient(new ClientData("Sidorov", "Nikolai", "RF, NSK", "+72589631478", "3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1"));
        app.getClientHelper().createClient(contakt);
        //подсчет кол-ва групп (строк) после добавления
        //int after = app.getClientHelper().getClientCount();
        List<ClientData> after = app.getClientHelper().getClientList();
        //проверка, сравнение
        // Assert.assertEquals(after , before+1);
        Assert.assertEquals(after.size(), before.size() + 1);


        //сравнеие списков построчно целиком, как задам в шаблоне equals(Object o) , toString,  hashCode() в  листе GroupData

        int max = 0;
        for (ClientData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        contakt.setId(max);
        before.add(contakt);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }

}
