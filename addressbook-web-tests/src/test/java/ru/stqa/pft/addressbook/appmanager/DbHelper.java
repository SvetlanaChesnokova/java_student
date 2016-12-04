package ru.stqa.pft.addressbook.appmanager;


import com.sun.jna.platform.win32.WinDef;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by chesnokova.sa on 01.12.2016.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){
        //установка соединения с БД.
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for ( GroupData group : result ) {
            System.out.println(group);
            }
        session.getTransaction().commit();
        session.close();
        return  new Groups(result);
    }

    public Clients clients() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //в этой строке можно указывать запрос, который влияет на вывод данных
        //deprecated - дата и время удаления контакта
        List<ClientData> result = session.createQuery("from ClientData where deprecated = '0000-00-00'").list();
        for ( ClientData contact : result ) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
        return  new Clients(result);
    }


    public Clients grContact() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //в этой строке можно указывать запрос, который влияет на вывод данных
        List<ClientData> result = session.createQuery("from ClientData where deprecated = '0000-00-00' ").list();

        session.getTransaction().commit();
        session.close();

        for ( ClientData contact : result ) {
            System.out.println(contact);
            System.out.println(contact.getGroups());

           // return  new Clients(contact.getGroups());

        }
        return  new Clients(result);
    }

}
