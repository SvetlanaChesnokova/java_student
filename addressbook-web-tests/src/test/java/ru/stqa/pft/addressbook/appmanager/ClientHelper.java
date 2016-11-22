package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.Clients;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Светлана on 06.11.2016.
 */
public class ClientHelper extends HelperBase{

  public ClientHelper(WebDriver wd) {
    super(wd);
  }

  public void initAddNewCreation() {
    click(By.linkText("add new"));
  }

  public void create(ClientData clientData) {
    //все шаги для создания контакта с минимальным набором данных
    initAddNewCreation();
    emllAddNewForm( clientData, true);
    submitAddNewCreation();
    clientCache = null;
    returnAddNewCreation();
  }


   public void modify(ClientData contakt) {
    initAddNewModificationById(contakt.getId());
    emllAddNewForm(contakt, false);
    ubdateAddNewCreation();
    clientCache = null;
    returnAddNewCreation();
  }

  public void delete(ClientData clientData) {
    selectAddNewById(clientData.getId());
    initAddNewDelete();
    clientCache = null;
    initAddNewAlert();
  }

  public void submitAddNewCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnAddNewCreation() {
    click(By.linkText("home page"));
  }

  public void initHome() {
    click(By.linkText("home"));
  }


  public void selectAddNewById(int id) {
        //выбор заданного - передаваемого из теста
        wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
        //выбор любого из списка
        // click(By.name("selected[]"));
    }

  public void initAddNewDelete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initAddNewAlert() {
    wd.switchTo().alert().accept();
  }


  public void initAddNewModificationById(int id) {
          //wd.findElements(By.cssSelector("img[alt='Edit']")).get(num).click();
          wd.findElement(By.xpath("//input[@id='"+ id +"']//..//..//img[@alt='Edit']")).click();
    }

  public void ubdateAddNewCreation() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void emllAddNewForm(ClientData clientData, boolean creation) {

    type(By.name("lastname"), clientData.getP_lastname());
    type(By.name("firstname"), clientData.getP_firstnam());
    type(By.name("homepage"), clientData.getP_homepage());
    type(By.name("middlename"), clientData.getP_middlename());
    type(By.name("nickname"), clientData.getP_nickname());
    type(By.name("title"), clientData.getP_title());
    type(By.name("company"), clientData.getP_company());
    type(By.name("fax"), clientData.getP_fax());
    type(By.name("address2"), clientData.getP_address2());
    type(By.name("phone2"), clientData.getP_phone2());
    type(By.name("notes"), clientData.getP_notes());
    type(By.name("address"), clientData.getP_address());

    //сделала для себя проверку, на случай если не заполню поля, которые не должны быть пустыми
    if (clientData.getP_phones() != null ) {
      type(By.name("mobile"), clientData.getP_phones());
    } else {
      type(By.name("mobile"), "нет_данных");
    }

    if (clientData.getP_home() != null ) {
      type(By.name("home"), clientData.getP_home());
    } else {
      type(By.name("home"), "нет_данных");
    }

    if (clientData.getP_work() != null ) {
      type(By.name("work"), clientData.getP_work());
    } else {
      type(By.name("work"), "нет_данных");
    }

    if (clientData.getP_email() != null ) {
      type(By.name("email"), clientData.getP_email());
    } else {
      type(By.name("email"), "нет_данных");
    }

    if (clientData.getP_email2() != null ) {
      type(By.name("email2"), clientData.getP_email2());
    } else {
      type(By.name("email2"), "нет_данных");
    }

    if (clientData.getP_email3() != null ) {
      type(By.name("email3"), clientData.getP_email3());
    } else {
      type(By.name("email3"), "нет_данных");
    }


    //проверка на то какая форма создание/изменение
    // обязательно надо передавать значение параметра, при создании контакта, по которому осуществляется проверка
    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(clientData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
   /* //проверка на наличие элемента, лучше проверять какая форма, как выше
    if (isElementPresent(By.name("new_group"))){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(clientData.getGroup());
    }*/
  }


  //поменяла на список элементов
  public int count() {
    //явное ожидание элемента таблицы, и ожидание закрытия всплывающего окна
    WebElement selected = wait.until(presenceOfElementLocated(By.name("selected[]")));
    return wd.findElements(By.name("selected[]")).size();
  }

  private Clients clientCache = null;

  public Clients all() {
      if (clientCache != null) {
          return  new Clients(clientCache);
      }
    //явное ожидание элемента таблицы, и ожидание закрытия всплывающего окна
    WebElement selected = wait.until(presenceOfElementLocated(By.name("entry")));
     clientCache = new Clients();
    // получить список Web елементов, которые на тег span и класс group
    List<WebElement> elements = wd.findElements(By.name("entry"));
    //Цикл по списку элиментов, чтобы считать их название
    for (WebElement element : elements) {
      List<WebElement> stol = element.findElements(By.tagName("td"));
      //поиск элемента внутри другого           .get(num).
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = stol.get(1).getText();
      String firstname = stol.get(2).getText();
      //разбиваем строку телефонов на фрагменты, спомощью split("\n")
      String [] phones = stol.get(5).getText().split("\n");
      clientCache.add(new ClientData().withId(id).withP_lastname(lastname).withP_firstnam(firstname)
                 .withP_home(phones[0]).withP_phones(phones[1]).withP_work(phones[2]));
    }
    return new Clients(clientCache);
  }


    public ClientData infoFromEditForm(ClientData contact) {
     initContactModificationById(contact.getId());
     String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
     String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
     String home = wd.findElement(By.name("home")).getAttribute("value");
     String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
     String work = wd.findElement(By.name("work")).getAttribute("value");
     wd.navigate().back();
     return new ClientData().withId(contact.getId()).withP_firstnam(firstname).withP_lastname(lastname)
             .withP_home(home).withP_phones(mobile).withP_work(work);
    }

  private void initContactModificationById(int id) {
    //Несколько способов поиска и обращения к объекту, редактирования формы:
    //1-й способ, состоит из 4-х строк
    // %s- искользуется как переменное значение в коде в которое можно подставить даннные, как :id -в оракле
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    //поднятие на 2-а уровня выше , к родительскому дереву
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //2-й способ
    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
    //3-й способ
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //4-й способ
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

  }
}
