package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created by chesnokova.sa on 15.12.2016.
 */
public class RestAssuredTest {

  @BeforeClass
  public void init() {
      //по описанию надо добавить  "LSGjeU4yP1X493ud1hNniA==" - вместо логина, а пароль передать пустым
      RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }


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

  private Set<Issue> getIssues() throws IOException {
         String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
         JsonElement parsed = new JsonParser().parse(json);
         JsonElement issues = parsed.getAsJsonObject().get("issues");
         return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
     }

  private int createIssue(Issue newIssue) throws IOException {
       String json = RestAssured.given()
              .parameter("subject", new Issue().getSubject())
              .parameter("description", new Issue().getDescription())
              .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
      //возвращаем id созданного баг-репорта
         return parsed.getAsJsonObject().get("issue_id").getAsInt();
     }

}
