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
  protected static final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
 // protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
 // protected static final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
