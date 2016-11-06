package ru.stqa.pft.addressbook.model;

/**
 * Created by Светлана on 06.11.2016.
 */
public class ClientData {

  private final String p_email;
  private final String p_email2;
  private final String p_email3;
  private final String p_homepage;


  public ClientData (String p_email, String p_email2,String p_email3, String p_homepage) {
    this.p_email = p_email;
    this.p_email2 = p_email2;
    this.p_email3 = p_email3;
    this.p_homepage = p_homepage;

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

}
