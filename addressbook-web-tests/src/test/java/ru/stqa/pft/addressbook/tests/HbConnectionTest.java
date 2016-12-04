package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by chesnokova.sa on 30.11.2016.
 */
public class HbConnectionTest  {

    //процедура инициализации к БД
    private SessionFactory sessionFactory;

    @BeforeClass
    public void setUp() throws Exception {
       final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
       try {
          sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
       }
       catch (Exception e) {
         e.printStackTrace();
         StandardServiceRegistryBuilder.destroy( registry );
       }
     }



     @Test
     public void testHbConnection() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
          for ( GroupData group : result ) {
               System.out.println(group);
          }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHbConnection_Contact() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //в этой строке можно указывать запрос, который влияет на вывод данных
        List<ClientData> result = session.createQuery("from ClientData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();

        for ( ClientData contact : result ) {
            System.out.println(contact);
            System.out.println(contact.getGroups());
        }


    }
}
