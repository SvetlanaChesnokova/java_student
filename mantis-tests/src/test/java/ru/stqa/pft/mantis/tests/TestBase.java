package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static ru.stqa.pft.mantis.appmanager.SoapHelper.getMantisConnect;


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


  public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException {
         if (isIssueOpen(issueId)) {
             throw new SkipException("Ignored because of issue " + issueId);
         }
     }

 private boolean isIssueOpen(int issueId) throws RemoteException, MalformedURLException, ServiceException {
     MantisConnectPortType mc = getMantisConnect();
     IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    // System.out.println("Статус - " + issue.getStatus().toString());
     if (issue.getStatus().toString() == "resolved") {
      return false;
      } else {
      return true;
     }
  }




}
