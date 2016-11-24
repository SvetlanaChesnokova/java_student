package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by chesnokova.sa on 25.11.2016.
 */
public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //вынесена, подготовка теста
        app.contakt().initHome();
        app.goTo().gotoHomePage();
        //проверяем есть ли хоть одна запись
        if (app.contakt().all().size() == 0) {
            //если нет записи, то создаем ее
            app.contakt().create(new ClientData().withP_firstnam("Sidorov8").withP_lastname("Nikolai").withP_address("RF, NSK")
                    .withP_homepage("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
                    .withP_email3("Sidorov@erw.ru").withP_phones("357-1598").withGroup("test17")
                    .withP_home("741 85").withP_work("858(41) 4757"));
        }
    }


    @Test
    public void testContactEmail (){
        //переходим на главную страницу
        app.goTo().gotoHomePage();
        //загружаем множество контактов, выбор случайным образом
        ClientData contact = app.contakt().all().iterator().next();
        //загрузка контактов с сайта, из формы редактирования, для дальнейшего сравнения
        ClientData contactInfoFromEditFotm = app.contakt().infoFromEditForm(contact);

        //метод обратной проверки
        MatcherAssert.assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditFotm)));

    }

    private String  mergeEmail(ClientData contact) {
        //фильтрация и склеивание строк
        return Arrays.asList(contact.getP_email(),contact.getP_email2(),contact.getP_email3())
                .stream().filter((s) ->! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone) {
        //убираем из записи пробел , записывается так: "\\s"
        return phone.replaceAll("\\s", "");
    }


}
