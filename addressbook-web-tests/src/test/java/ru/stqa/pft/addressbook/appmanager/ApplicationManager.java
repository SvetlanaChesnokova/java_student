package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Светлана on 01.11.2016.
 */
public class ApplicationManager {
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ClientHelper clientHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
   // String browser = BrowserType.FIREFOX;
    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    //Ожидание всех элементов на форме, касательно всего проекта
   // wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
   // wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //DriverWait, явные ожидание конкретного элемента с заданным временем в секундах
    WebDriverWait wait = new WebDriverWait(wd, 10);
    // В GroupHelper не получается установить блок ожидания элемента selected. Где он должен быть, в какой вкладке?
//  WebElement selected = wait.until(presenceOfElementLocated(By.name("selected[]")));

    //серия действий для входа в систему
    wd.get("http://localhost/addressbook/");
    //правильней сделать для всех тестов один вход на главную станицу, поэтому изменила ссылку
    //wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    clientHelper = new ClientHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");

  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ClientHelper getClientHelper() {
    return clientHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
