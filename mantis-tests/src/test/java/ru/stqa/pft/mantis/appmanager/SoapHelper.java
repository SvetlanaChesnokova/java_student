package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by chesnokova.sa on 14.12.2016.
 */
public class SoapHelper {

    private ApplicationManager app;

     public SoapHelper(ApplicationManager app) {
         this.app = app;
     }

     public Set<Project> getProjects()  throws MalformedURLException, ServiceException, RemoteException {
         MantisConnectPortType mc = getMantisConnect();
         ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
         return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
         .collect(Collectors.toSet());
     }

    public static MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
         return new MantisConnectLocator()
     }

     public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
         //работа с бакрепортом
         MantisConnectPortType mc = getMantisConnect();
         //список катигорий для выбора
        String login = app.getProperty("web.adminLogin");
        String password = app.getProperty("web.adminPassword");

         String[] categories = mc.mc_project_get_categories(login, password, BigInteger.valueOf(issue.getProject().getId()));
         IssueData issueData = new IssueData();
         issueData.setSummary(issue.getSummary());
         issueData.setDescription(issue.getDescription());
         // ObjectRef - ссылка на проект
         issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
         //зброс катигорий
         issueData.setCategory(categories[0]);
         BigInteger issueId = mc.mc_issue_add(login, password, issueData);
         IssueData createdIssueData = mc.mc_issue_get(login, password, issueId);
         return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
                 .withDescription(createdIssueData.getDescription()).withProject( new Project().withId(createdIssueData.getProject().getId().intValue()));
     }

}
