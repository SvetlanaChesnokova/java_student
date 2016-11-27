package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by Светлана on 01.11.2016.
 */
public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ClientHelper clientHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException{
    String target = System.getProperty("target", "local");
       properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

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
   // WebDriverWait wait = new WebDriverWait(wd, 10);
    // В GroupHelper не получается установить блок ожидания элемента selected. Где он должен быть, в какой вкладке?
//  WebElement selected = wait.until(presenceOfElementLocated(By.name("selected[]")));

    //серия действий для входа в систему
    wd.get(properties.getProperty("web.baseUrl"));
    groupHelper = new GroupHelper(wd);
    clientHelper = new ClientHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));

  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ClientHelper contakt() {
    return clientHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
