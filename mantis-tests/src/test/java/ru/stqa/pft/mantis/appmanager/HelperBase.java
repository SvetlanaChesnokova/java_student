package ru.stqa.pft.mantis.appmanager;

import com.thoughtworks.selenium.webdriven.ElementFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by Светлана on 01.11.2016.
 */
public class HelperBase {

  protected WebDriver wd;
  protected ApplicationManager app;


  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
    //DriverWait, явные ожидание конкретного элемента с заданным временем в секундах
   // this.wd = new WebDriverWait(wd, 10);
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator); //используется во всех лекциях, поэтому оставила.
    //временно комментирую, блок очень нужный
    //if (text != null)   {
    if ((text != null) && !text.equals( wd.findElement(locator).getAttribute("value")))  {
      String existingText = wd.findElement(locator).getAttribute("value");
      //Проверка на совпадение текста, если совпадает, то не вводить.
      //Удобно и хорошо аптимизирует, если в поле большие значения.
      if (! text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }


  protected void attach(By locator, File file) {
    if (file != null) {
      // file.getAbsolutePath() - передача фактического пути к файлу  и его преобоазование в полный путь
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
    }
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

  protected boolean isElementPresent(By locator) {
    try{
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
