package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by Светлана on 01.11.2016.
 */
public class TestBase {
  // Выбор браузера для запусков тестов
  // драйверы для запуска в папке C:\tools
  // static самостаятельная глобальная переменная.
  //browser - парамет позволяет передавать вид браузера для запуска
  //в конфигурациях запуска прописывать -ea -Dbrowser=chrome, к примеру
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
 // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));
 // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.IE));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
