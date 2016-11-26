package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

//@XStreamAlias("group") - преобразование файла *.xml
@XStreamAlias("group")
public class GroupData {
  //@XStreamOmitField - пропустить вывод id поля в файле  *.xml
  @XStreamOmitField
  //final - знацит что это значение остается внутри этого метода, надо убрать, чтобы можно было изменить
  private int id  = Integer.MAX_VALUE;
  private String name;
  private String header;
  private String footer;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;

  }

  //hashCode - способ ускорения проверки, если hashCode не совпал, то дальше проверку equals можно не делать
  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  public GroupData withName(String name) {
    this.name = name;

    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

}
