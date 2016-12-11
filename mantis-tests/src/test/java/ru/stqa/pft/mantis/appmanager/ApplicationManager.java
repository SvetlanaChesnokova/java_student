package ru.stqa.pft.mantis.appmanager;

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


public class ApplicationManager{// implements WebDriver {
  private final Properties properties;
  WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;
  private SessionHelper sessionHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private DbHelper dbHelper;
  private JamesHelper jamesHelper;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException{
    String target = System.getProperty("target", "local");
       properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
    }

  public void stop() {
   if (wd != null)
    wd.quit();
  }

  public HttpSession newSession(){
    // можно выполнять сразу несколько сессий, под разными пользователями,
    // проверяя больше функционала и делегирование его. Используя сетивой протокол.
    return new HttpSession(this);
  }

  public String getProperty(String key) {
   return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null) {
      registrationHelper = new RegistrationHelper(this);
    }
    return registrationHelper;
  }

  public SessionHelper session() {
    if (sessionHelper == null) {
      sessionHelper = new SessionHelper(this);
    }
    return sessionHelper;
  }

  public FtpHelper ftp (){
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }


  public WebDriver getDriver() {
    //отдельный метод, для включения браузера, по необходимости
    //Ожидание всех элементов на форме, касательно всего проекта
    // wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
   // wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //DriverWait, явные ожидание конкретного элемента с заданным временем в секундах
    // WebDriverWait wait = new WebDriverWait(wd, 10);
    if (wd == null) {
      // String browser = BrowserType.FIREFOX;
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }

      //серия действий для входа в систему
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return  wd;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }


  public DbHelper db() {
    return dbHelper;
  }

  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }
}
