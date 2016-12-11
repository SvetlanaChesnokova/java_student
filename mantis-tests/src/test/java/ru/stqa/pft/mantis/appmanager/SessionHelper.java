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
  }

  public void resetPassword(){
    click(By.cssSelector("a[class='manage-menu-link']"));

    click(By.xpath("//ul[@class='menu']//a[.='Управление пользователями']"));

    //выбираем 4-го пользователя в первом столбце, у меня это  user1
    // user1
    click(By.xpath("//div/div[4]/div[3]/table/tbody/tr[4]/td[1]/a"));
    // нажимаем кнопку Reset Password
    click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
  }


}
