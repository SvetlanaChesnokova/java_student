package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Светлана on 01.11.2016.
 */
public class HelperBase {
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator); //используется во всех лекциях, поэтому оставила.
   /* //временно комментирую, блок очень нужный
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      //Проверка на совпадение текста, если совпадает, то не вводить.
      //Удобно и хорошо аптимизирует, если в поле большие значения.
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }*/
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  // работа с диалоговым окном, исключениями, ошибками
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } //описание исключения, при перехвате диалогового окна
    catch (NoAlertPresentException e) {
      return false;
    }
  }
}
