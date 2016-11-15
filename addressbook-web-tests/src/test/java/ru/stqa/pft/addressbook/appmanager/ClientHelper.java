package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ClientData;
import ru.stqa.pft.addressbook.model.GroupData;

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


  public void fillAddNewForm(String p_middlename, String p_nickname,
                             String p_title, String p_company) {
    type(By.name("middlename"), p_middlename);
    type(By.name("nickname"), p_nickname);
    type(By.name("title"), p_title);
    type(By.name("company"), p_company);
  }

  public void telephoneAddNewForm(String p_home, String p_work, String p_fax) {
    type(By.name("home"), p_home);
    type(By.name("work"), p_work);
    type(By.name("fax"), p_fax);
  }

  public void secondaryAddNewForm(String p_address2,String p_phone2, String p_notes) {
    type(By.name("address2"), p_address2);
    type(By.name("phone2"), p_phone2);
    type(By.name("notes"), p_notes);
  }

  public void create(ClientData clientData) {
    //все шаги для создания контакта с минимальным набором данных
    initAddNewCreation();
    emllAddNewForm( clientData, true);
    submitAddNewCreation();
    returnAddNewCreation();
  }

  public void modify(int index, ClientData contakt, String p_middlename, String p_nickname, String p_title, String p_company, String p_home, String p_work, String p_fax, String p_address2, String p_phone2, String p_notes) {
    initAddNewModification(index);
    fillAddNewForm(p_middlename, p_nickname, p_title, p_company );
    telephoneAddNewForm(p_home, p_work, p_fax);
    secondaryAddNewForm(p_address2,p_phone2, p_notes);
    emllAddNewForm(contakt, false);
    ubdateAddNewCreation();
    returnAddNewCreation();
  }

  public void delete(int index) {
    selectAddNew(index);
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

  public void selectAddNew(int index) {
    //выбор заданного - передаваемого из списка
    wd.findElements(By.name("selected[]")).get(index).click();
    //выбор любого из списка
    // click(By.name("selected[]"));
  }

  public void initAddNewDelete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initAddNewAlert() {
    wd.switchTo().alert().accept();
  }

  public void initAddNewModification(int num) {
    wd.findElements(By.cssSelector("img[alt='Edit']")).get(num).click();
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

    //проверка на то какая форма создание/изменение
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

    public int getClientCount() {
      return wd.findElements(By.name("selected[]")).size();
    }

  public List<ClientData> list() {
    //явное ожидание элемента таблицы, и ожидание закрытия всплывающего окна
    WebElement selected = wait.until(presenceOfElementLocated(By.name("entry")));
    List<ClientData> contakts = new ArrayList<ClientData>();
    // получить список Web елементов, которые на тег span и класс group
    List<WebElement> elements = wd.findElements(By.name("entry"));
    //Цикл по списку элиментов, чтобы считать их название
    for (WebElement element : elements) {
      List<WebElement> stol = element.findElements(By.tagName("td"));
      String lastname = stol.get(1).getText();
      String firstname = stol.get(2).getText();
      //поиск элемента внутри другого
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contakts.add(new ClientData().withId(id).withP_lastname(lastname).withP_firstnam(firstname));
    }
    return contakts;
  }
}
