package ru.stqa.pft.addressbook.model;

/**
 * Created by Светлана on 06.11.2016.
 */
public class ClientData {

  private final String p_lastname;
  private final String p_firstnam;
  private final String p_address;
  private final String p_phones;
  private final String p_email;
  private final String p_email2;
  private final String p_email3;
  private final String p_homepage;
  private  String group;


  public ClientData (String p_lastname, String p_firstnam, String p_address, String p_phones, String p_email, String p_email2, String p_email3, String p_homepage, String group) {
    this.p_lastname = p_lastname;
    this.p_firstnam = p_firstnam;
    this.p_address = p_address;
    this.p_phones = p_phones;
    this.p_email = p_email;
    this.p_email2 = p_email2;
    this.p_email3 = p_email3;
    this.p_homepage = p_homepage;
    this.group = group;
  }


  public String getP_email() {
    return p_email;
  }

  public String getP_email2 () {
    return p_email2;
  }

  public String getP_email3 () {
    return p_email3;
  }

  public String getP_homepage () {
    return p_homepage;
  }

  public String getGroup() {
    return group;
  }

  public String getP_firstnam() {
    return p_firstnam;
  }

  public String getP_address () {
    return p_address;
  }

  public String getP_lastname () {
    return p_lastname;
  }

  public String getP_phones () {
    return p_phones;
  }
}
