package ru.stqa.pft.mantis.appmanager;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.UserBD;
import ru.stqa.pft.mantis.model.UserData;

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

    public UserBD users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData").list();
        for ( UserData userBD : result ) {
            System.out.println(userBD);
            }
        session.getTransaction().commit();
        session.close();
        return  new UserBD(result);
    }


}
