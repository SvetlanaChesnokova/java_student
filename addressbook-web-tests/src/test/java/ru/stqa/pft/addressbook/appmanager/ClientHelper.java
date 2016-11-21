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
    returnAddNewCreation();
  }


   public void modify(ClientData contakt) {
    initAddNewModificationById(contakt.getId());
    //зделала разбивку на 3и группы для наглядности заполнения формы
    emllAddNewForm(contakt, false);
    ubdateAddNewCreation();
    returnAddNewCreation();
  }

  public void delete(ClientData clientData) {
    selectAddNewById(clientData.getId());
    initAddNewDelete();
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
    type(By.name("address"), clientData.getP_address());
    type(By.name("mobile"), clientData.getP_phones());
    type(By.name("email"), clientData.getP_email());
    type(By.name("email2"), clientData.getP_email2());
    type(By.name("email3"), clientData.getP_email3());
    type(By.name("homepage"), clientData.getP_homepage());
    type(By.name("middlename"), clientData.getP_middlename());
    type(By.name("nickname"), clientData.getP_nickname());
    type(By.name("title"), clientData.getP_title());
    type(By.name("company"), clientData.getP_company());
    type(By.name("home"), clientData.getP_home());
    type(By.name("work"), clientData.getP_work());
    type(By.name("fax"), clientData.getP_fax());
    type(By.name("address2"), clientData.getP_address2());
    type(By.name("phone2"), clientData.getP_phone2());
    type(By.name("notes"), clientData.getP_notes());

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


  public boolean isThereAClient() {
    //проверка на наличие объекта
    return isElementPresent(By.name("selected[]"));
  }

  public Clients all() {
    //явное ожидание элемента таблицы, и ожидание закрытия всплывающего окна
    WebElement selected = wait.until(presenceOfElementLocated(By.name("entry")));
     Clients contakts = new Clients();
    // получить список Web елементов, которые на тег span и класс group
    List<WebElement> elements = wd.findElements(By.name("entry"));
    //Цикл по списку элиментов, чтобы считать их название
    for (WebElement element : elements) {
      List<WebElement> stol = element.findElements(By.tagName("td"));
      String lastname = stol.get(1).getText();
      String firstname = stol.get(2).getText();
      //поиск элемента внутри другого           .get(num).
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contakts.add(new ClientData().withId(id).withP_lastname(lastname).withP_firstnam(firstname));


    }
    return contakts;
  }


}
