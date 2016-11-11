package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class AddNewCreationTests extends TestBase {

    @Test
    public void testAddNewCreation() {
        //тест для создания контакта
        //подсчет кол-ва строк до добавления
        //int before = app.getClientHelper().getClientCount();
        List<ClientData> before = app.getClientHelper().getClientList();
        app.getClientHelper().createClient(new ClientData("Sidorov","Nikolai", "RF, NSK","+72589631478", "3-147-258@", "Nikolai@tre", "Sidorov@erw.ru", "357-1598", "test1"));
        //подсчет кол-ва групп (строк) после добавления
        //int after = app.getClientHelper().getClientCount();
        List<ClientData> after = app.getClientHelper().getClientList();
        //проверка, сравнение
       // Assert.assertEquals(after , before+1);
        Assert.assertEquals(after.size() , before.size()+1);
    }

}
