package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {

  // Выбор браузера для запусков тестов
  // драйверы для запуска в папке C:\tools
  // static самостаятельная глобальная переменная.
  //browser - парамет позволяет передавать вид браузера для запуска
  //в конфигурациях запуска прописывать -ea -Dbrowser=chrome, к примеру
  public static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
 // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));
 // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.IE));

  @BeforeSuite (alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite
  public void tearDown() throws IOException {
   app.ftp().restore("config_inc.php.bak", "config_inc.php");
   app.stop();
  }


}
