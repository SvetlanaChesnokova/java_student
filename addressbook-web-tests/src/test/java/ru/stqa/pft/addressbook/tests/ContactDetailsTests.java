package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chesnokova.sa on 25.11.2016.
 */
public class ContactDetailsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //вынесена, подготовка теста
        app.contakt().initHome();
        app.goTo().gotoHomePage();
        //проверяем есть ли хоть одна запись
        if (app.contakt().all().size() == 0) {
            //если нет записи, то создаем ее
          app.contakt().create(new ClientData().withP_firstnam("Sidorov").withP_lastname("Nikolai")
                    .withP_email("3-147-258@").withP_email2("Nikolai@tre")
                    .withP_email3("Sidorov@erw.ru").withP_phones("357-1598").withP_address("RF, NSK")
                    .withP_home("741 85").withP_work("858(41) 4757"));
        }
    }


    @Test
    public void testContactDetails() {
        //переходим на главную страницу
        app.goTo().gotoHomePage();
        //загружаем множество контактов, выбор случайным образом
        ClientData contact = app.contakt().all().iterator().next();

        //загрузка контактов с сайта, из формы редактирования, для дальнейшего сравнения
        ClientData contactInfoFromEditFotm = app.contakt().infoFromEditForm(contact);

        //переходим на главную страницу
        app.goTo().gotoHomePage();
        //переход к детализации контакта, загрузка контактов с сайта, для дальнейшего сравнения
        ClientData contactInfoDetailsFotm = app.contakt().infoDetailsForm(contact);
        //для вывода на экран промежуточных результатов, и быстрого поиска его
        System.out.println("************************");
        System.out.println("from detail = " + (merge(contactInfoDetailsFotm)));
        System.out.println("from edit = " + (merge(contactInfoFromEditFotm)));
        System.out.println("************************");
        //метод обратной проверки
        MatcherAssert.assertThat((merge(contactInfoDetailsFotm)), equalTo(merge(contactInfoFromEditFotm)));

    }

    private String merge(ClientData contact) {
        //фильтрация и склеивание строк
        return Arrays.asList(contact.getP_lastname(),contact.getP_firstnam(),contact.getP_address(),
                contact.getP_home(),contact.getP_phones(),contact.getP_work())
                .stream().filter((s) ->! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}
