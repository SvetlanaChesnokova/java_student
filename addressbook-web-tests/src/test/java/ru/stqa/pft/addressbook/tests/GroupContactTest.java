package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

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


       /* Session session = sessionFactory.openSession();
        session.beginTransaction();
        //в этой строке можно указывать запрос, который влияет на вывод данных
        List<ClientData> result = session.createQuery("from ClientData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();

        for ( ClientData contact : result ) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }   */

      //  app.contakt().optGroupCn("[none]");

        //подсчет кол-ва строк до добавления
        Clients before = app.db().clients();
        ClientData grClient = before.iterator().next();
        GroupData groupChoice = app.db().groups().iterator().next();

   /*     app.contakt().optGroupC(groupChoice);
        app.wd.findElements(By.id("search_count")).size();
        System.out.println("gr - " + grClient.inGroup());

        app.contakt().optGroupCn("[none]");
    */
       /* app.contakt().optGroupC(groupChoice);

       <span id="search_count">3</span>
        id="search_count">0

        app.contakt().optGroupC(groupChoice);

        app.goTo().gotoHomePage();       */

        System.out.println("**************************");
        System.out.println("before - " + before);
        System.out.println("grClient - " + grClient);
        System.out.println("groupChoice - " + groupChoice);
       // System.out.println("grContact - " + grClient.inGroup());
        System.out.println("**************************");

        app.contakt().selectGroupC(grClient,groupChoice);

        //app.contakt().proverkaGroupC(grClient,groupChoice);

        app.goTo().gotoHomePage();
        app.contakt().optGroupCn("");
        //проверка в пользовательском интерфейсе / откльчаемый метод
        verifyClientListUI();
    }

}
