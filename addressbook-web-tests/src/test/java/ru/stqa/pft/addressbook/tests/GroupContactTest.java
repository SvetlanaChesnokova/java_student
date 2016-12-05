package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

/**
 * Created by chesnokova.sa on 04.12.2016.
 */
public class GroupContactTest extends TestBase {

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
        //проверяем есть ли хоть одна запись
        if (app.db().clients().size() == 0) {
            //если нет записи, то создаем ее
            Groups groups = app.db().groups();
            app.contakt().initHome();
            //переход на нужную форму
            app.goTo().gotoHomePage();
            app.contakt().create(new ClientData().withP_lastname("Sidorov").withP_firstnam("Nikolai").withP_address("RF, NSK")
                    .withP_phones("+72589631478").withP_email("3-147-258@").withP_email2("Nikolai@tre")
                    .withP_email3("Sidorov@erw.ru").withP_homepage("ttt").inGroup(groups.iterator().next())
                    .withP_home("741 85").withP_work("858(41) 4757"));
        }
    }


    @Test
    public void testGroupContact(){
        //тест для добавления контакта в группу
        //переход на нужную форму, при необходимосте
        app.goTo().gotoHomePage();

      //  app.contakt().optGroupCn("[none]");

        //подсчет кол-ва строк до добавления
        Clients before = app.db().clients();
        ClientData grClient = before.iterator().next();
        GroupData groupChoice = app.db().groups().iterator().next();


        System.out.println("**************************");
        System.out.println("before - " + before);
        System.out.println("grClient - " + grClient);
        System.out.println("groupChoice - " + groupChoice);
        System.out.println("**************************");

        app.contakt().selectGroupC(grClient,groupChoice);


      //  app.contakt().proverkaGroupC(grClient,groupChoice);

        app.goTo().gotoHomePage();
        app.contakt().optGroupCn("");
        //проверка в пользовательском интерфейсе / откльчаемый метод
        verifyClientListUI();
    }

}
