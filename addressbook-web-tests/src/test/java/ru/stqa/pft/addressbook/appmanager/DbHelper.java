package ru.stqa.pft.addressbook.appmanager;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
}
