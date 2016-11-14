package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * Created by Светлана on 01.11.2016.
 */
public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    //выбор заданного - передаваемого из списка
    wd.findElements(By.name("selected[]")).get(index).click();
    //выбор любого из списка
    // click(By.name("selected[]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void selectp() {
    //Работает
   //WebElement selected = wd.findElement(By.name("selected[]"));
   //Работает, явное ожидание
    WebElement selected = wait.until(presenceOfElementLocated(By.name("selected[]")));

  }

  public void createGroup(GroupData group) {
    //все шаги для создания группы
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public void modifyGroup(int index, GroupData group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    //проверка на наличие объекта selected на форме
    return isElementPresent(By.name("selected[]"));
  }

   //поменяла на список элементов
    public int getGroupCount() {
     return wd.findElements(By.name("selected[]")).size();
    }


  public List<GroupData> getGroupList() {
   List<GroupData> groups = new ArrayList<GroupData>();
    // получить список Web елементов, которые на тег span и класс group
   List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    //Цикл по списку элиментов, чтобы считать их название
    for (WebElement element : elements) {
      String name = element.getText();
      //поиск элемента внутри другого
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData(id, name, null, null);
      groups.add(group);
    }
   return groups;
  }





}
