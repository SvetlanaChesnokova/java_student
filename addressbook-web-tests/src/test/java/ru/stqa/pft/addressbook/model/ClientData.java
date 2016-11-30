package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

/**
 * Created by Светлана on 06.11.2016.
 */

//@XStreamAlias("group") - преобразование файла *.xml
@XStreamAlias("contact")
// @Entity- определяет привязанность к базе
@Entity
@Table(name = "addressbook")
public class ClientData {
  //@XStreamOmitField - пропустить вывод id поля в файле  *.xml
  @XStreamOmitField
  //final - знацит что это значение остается внутри этого метода, надо убрать, чтобы можно было изменить
  @Id
  @Column(name = "id")
  private int id  = Integer.MAX_VALUE;
  @Column(name = "lastname")
  //@Expose - помечают в *.json нужные поля
  @Expose
  private String p_lastname;
  @Column(name = "firstname")
  @Expose
  private String p_firstnam;
  @Column(name = "address")
  @Type(type = "text")
  @Expose
  private String p_address;
  @Column(name = "mobile")
  @Type(type = "text")
  @Expose
  private String p_phones;
  @Column(name = "email")
  @Type(type = "text")
  @Expose
  private String p_email;
  @Column(name = "email2")
  @Type(type = "text")
  @Expose
  private String p_email2;
  @Column(name = "email3")
  @Type(type = "text")
  @Expose
  private String p_email3;
  @Column(name = "homepage")
  @Type(type = "text")
  private String p_homepage;
  //group не будет извлекаться из БД, если указать @Transient
  @Transient
  @Expose
  private String group;
  @Column(name = "address2")
  @Type(type = "text")
  private String p_address2;
  @Column(name = "phone2")
  @Type(type = "text")
  private String p_phone2;
  @Column(name = "notes")
  @Type(type = "text")
  private String p_notes;
  @Column(name = "home")
  @Type(type = "text")
  private String p_home;
  @Column(name = "work")
  @Type(type = "text")
  private String p_work;

  @Override
  public String toString() {
    return "ClientData{" +
            "id=" + id +
            ", p_lastname='" + p_lastname + '\'' +
            ", p_firstnam='" + p_firstnam + '\'' +
            '}';
  }

  @Type(type = "text")
  @Column(name = "fax")
  private String p_fax;
  @Column(name = "middlename")
  private String p_middlename;
  @Column(name = "nickname")
  private String p_nickname;
  @Column(name = "title")
  @Expose
  private String p_title;
  @Column(name = "company")
  private String p_company;
  @Transient
  @Expose
  private String allPhones;
  @Transient
  @Expose
  private String allEmail;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }

  public ClientData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  public String getAllEmail() {
    return allEmail;
  }

  public ClientData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }


  public String getAllPhones() {
    return allPhones;
  }

  public ClientData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    //  возвращает this, для вытягивания в цепочку
    return this;
  }



  public ClientData withP_home(String p_home) {
    this.p_home = p_home;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ClientData that = (ClientData) o;

    if (id != that.id) return false;
    if (p_lastname != null ? !p_lastname.equals(that.p_lastname) : that.p_lastname != null) return false;
    return p_firstnam != null ? p_firstnam.equals(that.p_firstnam) : that.p_firstnam == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (p_lastname != null ? p_lastname.hashCode() : 0);
    result = 31 * result + (p_firstnam != null ? p_firstnam.hashCode() : 0);
    return result;
  }

  public ClientData withP_work(String p_work) {
    this.p_work = p_work;
    return this;
  }

  public ClientData withP_fax(String p_fax) {
    this.p_fax = p_fax;
    return this;
  }

  public ClientData withP_middlename(String p_middlename) {
    this.p_middlename = p_middlename;
    return this;
  }

  public ClientData withP_nickname(String p_nickname) {
    this.p_nickname = p_nickname;
    return this;
  }

  public ClientData withP_title(String p_title) {
    this.p_title = p_title;
    return this;
  }

  public ClientData withP_company(String p_company) {
    this.p_company = p_company;
    return this;
  }

  public ClientData withP_address2(String p_address2) {
    this.p_address2 = p_address2;
    return this;
  }

  public ClientData withP_phone2(String p_phone2) {
    this.p_phone2 = p_phone2;
    return this;
  }

  public ClientData withP_notes(String p_notes) {
    this.p_notes = p_notes;
    return this;
  }

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


  public String getP_home() {
    return p_home;
  }

  public String getP_work() {
    return p_work;
  }

  public String getP_fax() {
    return p_fax;
  }

  public String getP_middlename() {
    return p_middlename;
  }

  public String getP_nickname() {
    return p_nickname;
  }

  public String getP_title() {
    return p_title;
  }

  public String getP_company() {
    return p_company;
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

  public String getP_address2() {
    return p_address2;
  }

  public String getP_phone2() {
    return p_phone2;
  }

  public String getP_notes() {
    return p_notes;
  }
}
