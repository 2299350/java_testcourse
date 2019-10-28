package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@XStreamAlias("group")
@Entity // for hibernate
@Table (name = "group_list") // Shows hibernate the name of the table
public class GroupData {
  @Id
  @Column (name = "group_id") // Shows hibernate the name in DB
  @XStreamOmitField // it means to avoid adding this to XML
  private int id = Integer.MAX_VALUE;
  @Expose // it means add this to JSON
  @Column (name = "group_name") // Shows hibernate the name in DB
  private String name;
  @Expose
  @Column (name = "group_header") // Shows hibernate the name in DB
  @Type(type = "text") // Shows hibernate the type of the field
  private String header;
  @Expose
  @Column (name = "group_footer") // Shows hibernate the name in DB
  @Type(type = "text") // Shows hibernate the type of the field
  private String footer;

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            '}';
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}




