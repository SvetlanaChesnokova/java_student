package ru.stqa.pft.rest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by chesnokova.sa on 15.12.2016.
 */
public class RestTest extends TestBase{


  @Test
  public void testCreateIssue() throws IOException {
         Set<Issue> oldIssues = getIssues();
         Issue newIssue = new Issue().withSubject("Test issue").withDescription("New twst issue");
         int issueId = createIssue(newIssue);
      //добовляем новую строку баг-репорта
         Set<Issue> newIssues = getIssues();
         oldIssues.add(newIssue.withId(issueId));
      //сравниваем старое/новое значение баг-репортов
         Assert.assertEquals(newIssues, oldIssues);
     }



    @Test
    public void testCheckBolean () throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000002);
        System.out.println("Задание №22: Реализовать интеграцию тестов с баг-трекером Bugify");
    }


}
