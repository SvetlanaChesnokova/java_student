package ru.stqa.pft.addressbook.tests;

public class fillGroupForm {
  private final String getFirstName;
  private final String getMiddleName;
  private final String getLastName;
  private final String getNickname;
  private final String getTitle;
  private final String getCompany;
  private final String getAddress;
  private final String getHome;
  private final String getMobile;
  private final String getWork;
  private final String getFax;
  private final String getEmail;

  public fillGroupForm(String getFirstName, String getMiddleName, String getLastName, String getNickname, String getTitle, String getCompany, String getAddress, String getHome, String getMobile, String getWork, String getFax, String getEmail) {
    this.getFirstName = getFirstName;
    this.getMiddleName = getMiddleName;
    this.getLastName = getLastName;
    this.getNickname = getNickname;
    this.getTitle = getTitle;
    this.getCompany = getCompany;
    this.getAddress = getAddress;
    this.getHome = getHome;
    this.getMobile = getMobile;
    this.getWork = getWork;
    this.getFax = getFax;
    this.getEmail = getEmail;
  }

  public String getGetFirstName() {
    return getFirstName;
  }

  public String getGetMiddleName() {
    return getMiddleName;
  }

  public String getGetLastName() {
    return getLastName;
  }

  public String getGetNickname() {
    return getNickname;
  }

  public String getGetTitle() {
    return getTitle;
  }

  public String getGetCompany() {
    return getCompany;
  }

  public String getGetAddress() {
    return getAddress;
  }

  public String getGetHome() {
    return getHome;
  }

  public String getGetMobile() {
    return getMobile;
  }

  public String getGetWork() {
    return getWork;
  }

  public String getGetFax() {
    return getFax;
  }

  public String getGetEmail() {
    return getEmail;
  }
}
