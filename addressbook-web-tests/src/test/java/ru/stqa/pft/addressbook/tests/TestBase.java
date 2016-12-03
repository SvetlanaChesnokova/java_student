package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Светлана on 01.11.2016.
 */
public class TestBase {
  //для логирования данных
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  // Выбор браузера для запусков тестов
  // драйверы для запуска в папке C:\tools
  // static самостаятельная глобальная переменная.
  //browser - парамет позволяет передавать вид браузера для запуска
  //в конфигурациях запуска прописывать -ea -Dbrowser=chrome, к примеру
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
 // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));
 // protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser",BrowserType.IE));

  @BeforeSuite (alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

   @BeforeMethod
  public void logTestStart(java.lang.reflect.Method m, Object[] p){
     //logger можно выводить разного типа и ошибки и информацию....
     logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
   }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(java.lang.reflect.Method m){
    logger.info("Stop test " + m.getName());
  }


  public void verifyGroupListUI() {
    // возможность отключения проверки, настраивается в концигурации  -DverifyUI=true
    if(Boolean.getBoolean("verifyUI")) {
      //данные из БД
      Groups dbGroups = app.db().groups();
      //данные из пользовательского интерфейса
      Groups uiGroups = app.group().all();
      //данные БД упрощаем и берем только пару столбцов
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }


  public void verifyClientListUI()  {
    // возможность отключения проверки, настраивается в концигурации  -DverifyUI=true
    if(Boolean.getBoolean("verifyUI")) {
      //данные из БД
      Clients dbClients = app.db().clients();
      //данные из пользовательского интерфейса
      Clients uiClients = app.contakt().all();
      //данные БД упрощаем и берем только пару столбцов
      assertThat(uiClients, equalTo(dbClients.stream()
              .map((g) -> new ClientData().withId(g.getId()).withP_lastname(g.getP_lastname())
              .withP_firstnam(g.getP_firstnam()).withP_middlename(g.getP_middlename()).withP_address(g.getP_address()))
              .collect(Collectors.toSet())));

    }
  }



}
