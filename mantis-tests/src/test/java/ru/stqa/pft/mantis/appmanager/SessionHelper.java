package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserBD;
import ru.stqa.pft.mantis.model.UserData;

/**
 * Created by Светлана on 01.11.2016.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    //открываем страницу "входа"
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"),username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Войти']"));

       // assertTrue(wd.findElement(By.xpath(String.format("<span id=\"logged-in-user\">"))));
  }


      private UserData userBD = null;

      public UserData selectUser() {
        UserBD userBDs = app.db().users();
          for (UserData userBDB: userBDs) {
        if (userBDB.getName() == "administrator") {
            userBDs.iterator().next();
         } else {
            return userBD = userBDB;
         }
          }
          return userBD;
      }


  public void resetPassword(int id){
    click(By.cssSelector("a[class='manage-menu-link']"));

    click(By.xpath("//ul[@class='menu']//a[.='Управление пользователями']"));

    //выбираем 5-го пользователя в первом столбце, у меня это  user1481330452267
    // user1481330452267
  //  click(By.xpath("//div/div[4]/div[3]/table/tbody/tr[5]/td[1]/a"));
    click (By.cssSelector("a[href='manage_user_edit_page.php?user_id="+ id +"']"));
    // нажимаем кнопку Reset Password
    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }


}
