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

  public void submitAddNewCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnAddNewCreation() {
    click(By.linkText("home page"));
  }

  public void initAddNewHome() {
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

  public void initAddNewModification(String num) {
    // tr[2] - строка в таблице  td[8] - столбец в таблице
    // там находится элемент на который надо нажать для редактирования контакта
    click(By.xpath("//table[@id='maintable']/tbody/tr["+num+"]/td[8]/a/img"));
    // click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
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


  public void createClient(ClientData clientData) {
    //все шаги для создания контакта с минимальным набором данных
    initAddNewCreation();
    emllAddNewForm( clientData, true);
    submitAddNewCreation();
    returnAddNewCreation();
  }

  public boolean isThereAClient() {
    //проверка на наличие объекта
    return isElementPresent(By.name("selected[]"));
  }

    public int getClientCount() {
      return wd.findElements(By.name("selected[]")).size();
    }

  public List<ClientData> getClientList() {
    //явное ожидание элемента таблицы, и ожидание закрытия всплывающего окна
    WebElement selected = wait.until(presenceOfElementLocated(By.name("entry")));
    List<ClientData> contakts = new ArrayList<ClientData>();
    // получить список Web елементов, которые на тег span и класс group
    List<WebElement> elements = wd.findElements(By.name("entry"));
    //Цикл по списку элиментов, чтобы считать их название
    for (WebElement element : elements) {
      String lastname = element.getText();
      String firstname = element.getText();
      //String firstname = element.getText(); // надо усложнить
      ClientData group = new ClientData(lastname, firstname, null, null, null, null, null, null, null);
      contakts.add(group);
    }
    return contakts;
  }
}
