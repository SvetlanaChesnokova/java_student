package ru.stqa.pft.addressbook.model;

/**
 * Created by Светлана on 06.11.2016.
 */
public class ClientData {
  private int id  = Integer.MAX_VALUE;
  private String p_lastname;
  private String p_firstnam;
  private String p_address;
  private String p_phones;
  private String p_email;
  private String p_email2;
  private String p_email3;
  private String p_homepage;
  private String group;

  public ClientData withP_lastname(String p_lastname) {
    this.p_lastname = p_lastname;
    return this;
  }

  public ClientData withP_firstnam(String p_firstnam) {
    this.p_firstnam = p_firstnam;
    return this;
  }

  public ClientData withP_address(String p_address) {
    this.p_address = p_address;
    return this;
  }

  public ClientData withP_phones(String p_phones) {
    this.p_phones = p_phones;
    return this;
  }

  public ClientData withP_email(String p_email) {
    this.p_email = p_email;
    return this;
  }

  public ClientData withP_email2(String p_email2) {
    this.p_email2 = p_email2;
    return this;
  }

  public ClientData withP_email3(String p_email3) {
    this.p_email3 = p_email3;
    return this;
  }

  public ClientData withP_homepage(String p_homepage) {
    this.p_homepage = p_homepage;
    return this;
  }

  public ClientData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ClientData withId(int id) {
    this.id = id;
    return this;
  }



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



  @Override
  public String toString() {
    return "ClientData{" +
            "id='" + id + '\'' +
            ", p_lastname='" + p_lastname + '\'' +
            ", p_firstnam='" + p_firstnam + '\'' +
            '}';
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

  public void setId(int id) {
        this.id = id;
  }
}
