package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



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

        click(By.cssSelector("a[class='manage-menu-link']"));

       //class="menu" <a href="/mantisbt-1.3.4/manage_user_page.php">Управление пользователями</a>
        click(By.cssSelector("ul[class='menu']"));

  }

 /* public static void resetPassword(){
    //<a class="manage-menu-link" href="/mantisbt-1.3.4/manage_overview_page.php">управление</a
   // click(By.cssSelector("a[class='manage-menu-link']"));
  };
        */

}
