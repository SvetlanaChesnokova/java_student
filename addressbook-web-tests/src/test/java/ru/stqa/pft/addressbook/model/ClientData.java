package ru.stqa.pft.addressbook.model;

/**
 * Created by Светлана on 06.11.2016.
 */
public class ClientData {
  private int id;
  private final String p_lastname;
  private final String p_firstnam;
  private final String p_address;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ClientData that = (ClientData) o;

    if (p_lastname != null ? !p_lastname.equals(that.p_lastname) : that.p_lastname != null) return false;
    return p_firstnam != null ? p_firstnam.equals(that.p_firstnam) : that.p_firstnam == null;

  }

  @Override
  public int hashCode() {
    int result = p_lastname != null ? p_lastname.hashCode() : 0;
    result = 31 * result + (p_firstnam != null ? p_firstnam.hashCode() : 0);
    return result;
  }

  private final String p_phones;
  private final String p_email;
  private final String p_email2;
  private final String p_email3;
  private final String p_homepage;
  private  String group;

  @Override
  public String toString() {
    return "ClientData{" +
            "id='" + id + '\'' +
            ", p_lastname='" + p_lastname + '\'' +
            ", p_firstnam='" + p_firstnam + '\'' +
            '}';
  }

  public ClientData (String p_lastname, String p_firstnam, String p_address, String p_phones, String p_email, String p_email2, String p_email3, String p_homepage, String group) {
    this.id = Integer.MAX_VALUE;
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

  public void setId(int id) {
    this.id = id;
  }

  public ClientData (int id, String p_lastname, String p_firstnam, String p_address, String p_phones, String p_email, String p_email2, String p_email3, String p_homepage, String group) {
    this.id = id;
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

  public int getId() {
    return id;
  }
}
