package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

/**
 * Created by chesnokova.sa on 30.11.2016.
 */
public class DbConnectionTest extends TestBase {
    @Test
    public void testDbConnection(){
        Connection conn = null;

        try {
            //:3306 -порт ; addressbook - название базы; user= &password=  -для соединения с базой ;
            // serverTimezone=UTC& - таймзону в адресе базы данных.
            //Java-программа обращается к базе данных по сети, при этом Java-программа и СУБД могут работать на разных машинах, находящихся в разных часовых поясах.
            conn =
                    DriverManager.getConnection ("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()){
               groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
            }
            //  закрывает память
            rs.close();
            // не будем больше выполнять запросы
            st.close();
            //закрываем соединение с БД
            conn.close();

            System.out.println(groups);

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }




    @Test
    public void testDbConnectionGC(){
        //0412
        Connection conn = null;

        try {
            conn =
                    DriverManager.getConnection ("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            //ag.group_id, ad.*
            /*ResultSet rs = st.executeQuery("select ad.* from addressbook ad" +
                    " where ad.deprecated = '0000-00-00' ");   */

            GroupData groupChoice = app.db().groups().iterator().next();


            ResultSet rs = st.executeQuery("select ag.id  from " +
                    " address_in_groups ag  where ag.group_id=" + groupChoice.getId()); //135 and ag.id=ad.id ");

           /* Groups groups = new Groups();
            while (rs.next()){
                groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
            }         */
            //  закрывает память
            rs.close();
            // не будем больше выполнять запросы
            st.close();
            //закрываем соединение с БД
            conn.close();

         //   System.out.println(groups);
            System.out.println("**************************");
            System.out.println("rez - " + rs);
            System.out.println("groupChoice.getId() - " + groupChoice.getId());
            System.out.println("**************************");

            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
