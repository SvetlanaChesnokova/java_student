package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectGroupById(int id) {
    //выбор случайным образом элемента из списка
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
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

  public void create(GroupData group) {
    //все шаги для создания группы
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }


  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
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


   public Set<GroupData> all() {
    Set<GroupData> groups = new HashSet<GroupData>();
    // получить список Web елементов, которые на тег span и класс group
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    //Цикл по списку элиментов, чтобы считать их название
    for (WebElement element : elements) {
      String name = element.getText();
      //поиск элемента внутри другого
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }




}
