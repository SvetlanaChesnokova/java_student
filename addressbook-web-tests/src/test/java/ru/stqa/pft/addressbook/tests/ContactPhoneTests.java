package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by chesnokova.sa on 22.11.2016.
 */
public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        Groups groups = app.db().groups();
        //вынесена, подготовка теста
        app.contakt().initHome();
        app.goTo().gotoHomePage();
        //проверяем есть ли хоть одна запись
        if (app.contakt().all().size() == 0) {
            //если нет записи, то создаем ее
            app.contakt().create(new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai").withP_address("RF, NSK")
                    .withP_homepage("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
                    .withP_email3("Sidorov@erw.ru").withP_phones("357-1598").inGroup(groups.iterator().next())
                    .withP_home("741 85").withP_work("858(41) 4757"));
        }
    }


    @Test
    public void testContactPhones() {
        //переходим на главную страницу
        app.goTo().gotoHomePage();
        //загружаем множество контактов, выбор случайным образом
        ClientData contact = app.contakt().all().iterator().next();
        //загрузка контактов с сайта, из формы редактирования, для дальнейшего сравнения
        ClientData contactInfoFromEditFotm = app.contakt().infoFromEditForm(contact);

        //метод обратной проверки
        MatcherAssert.assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditFotm)));
    }

    private String mergePhones(ClientData contact) {
        //фильтрация и склеивание строк
        return Arrays.asList(contact.getP_home(),contact.getP_phones(),contact.getP_work())
                .stream().filter((s) ->! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        //убираем из записи пробел , записывается так: "\\s"
        //убираем из записи () скобки и знак тире - , записывается так: "[-()]"
        //можно указать так: replaceAll("[a-z]", "") - это для замены определенного вида на пробел, в данном случае букв.
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
