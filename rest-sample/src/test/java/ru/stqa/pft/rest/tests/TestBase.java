package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;



public class TestBase {

    RestAssured.proxy("192.168.0.2", 8080);


    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());

    }

    private Executor getExecutor() {
        //по описанию надо добавить  "LSGjeU4yP1X493ud1hNniA==" - вместо логина, а пароль передать пустым
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", new Issue().getSubject()),
                        new BasicNameValuePair("description", new Issue().getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        //возвращаем id созданного баг-репорта
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }


 public void skipIfNotFixed(int issueId) throws RemoteException, MalformedURLException, ServiceException {
         if (isIssueOpen(issueId)) {
             throw new SkipException("Ignored because of issue " + issueId);
         }
     }

 private boolean isIssueOpen(int issueId) throws IOException, ServiceException {
     String issue = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
             .returnContent().asString();
     System.out.println("Статус - " + issue.getStatus().getName());
     if (issue.getStatus().getName() == "resolved") {
      return false;
      } else {
      return true;
     }
  }



}
