package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chesnokova.sa on 22.11.2016.
 */
public class ContactPhoneTests extends TestBase {

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
    public void testContactPhones() {
        //переходим на главную страницу
        app.goTo().gotoHomePage();
        //загружаем множество контактов
        ClientData contact = app.contakt().all().iterator().next();
        //загрузка контактов с сайта, из формы редактирования, для дальнейшего сравнения
        ClientData contactInfoFromEditFotm = app.contakt().infoFromEditForm(contact);

        MatcherAssert.assertThat(contact.getP_home(), equalTo(cleaned(contactInfoFromEditFotm.getP_home())));
        MatcherAssert.assertThat(contact.getP_phones(), equalTo(cleaned(contactInfoFromEditFotm.getP_phones())));
        MatcherAssert.assertThat(contact.getP_work(), equalTo(cleaned(contactInfoFromEditFotm.getP_work())));

    }

    private String cleaned(String phone) {
        //убираем из записи пробел , записывается так: "\\s"
        //убираем из записи () скобки и знак тире - , записывается так: "[-()]"
        //можно указать так: replaceAll("[a-z]", "") - это для замены определенного вида на пробел, в данном случае букв.
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
