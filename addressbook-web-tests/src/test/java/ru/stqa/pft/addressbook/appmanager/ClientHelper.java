package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ClientData;

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

  public void fillAddNewForm(String p_firstnam, String p_middlename,String p_lastname, String p_nickname,
                             String p_title, String p_company, String p_address) {
    type(By.name("firstname"), p_firstnam);
    type(By.name("middlename"), p_middlename);
    type(By.name("lastname"), p_lastname);
    type(By.name("nickname"), p_nickname);
    type(By.name("title"), p_title);
    type(By.name("company"), p_company);
    type(By.name("address"), p_address);
  }

  public void telephoneAddNewForm(String p_home, String p_mobile,String p_work, String p_fax) {
    type(By.name("home"), p_home);
    type(By.name("mobile"), p_mobile);
    type(By.name("work"), p_work);
    type(By.name("fax"), p_fax);
  }

  public void emlAddNewForm(String p_email, String p_email2,String p_email3, String p_homepage) {
    type(By.name("email"), p_email);
    type(By.name("email2"), p_email2);
    type(By.name("email3"), p_email3);
    type(By.name("homepage"), p_homepage);
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

  public void selectAddNew() {
    click(By.name("selected[]"));
  }

  public void initAddNewDelete() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initAddNewAlert() {
    wd.switchTo().alert().accept();
  }

  public void initAddNewModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[17]/td[8]/a/img"));
  }

  public void ubdateAddNewCreation() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void emllAddNewForm(ClientData clientData) {
    type(By.name("email"), clientData.getP_email());
    type(By.name("email2"), clientData.getP_email2());
    type(By.name("email3"), clientData.getP_email3());
    type(By.name("homepage"), clientData.getP_homepage());
  }
}