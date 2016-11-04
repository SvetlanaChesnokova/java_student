package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by Светлана on 01.11.2016.
 */
public class TestBase {
  // Выбор браузера для запусков тестов
  // драйверы для запуска в папке C:\tools
  protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
 // protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);
 // protected final ApplicationManager app = new ApplicationManager(BrowserType.IE);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}
