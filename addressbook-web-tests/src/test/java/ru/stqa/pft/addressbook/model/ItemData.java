package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity // for hibernate
@Table(name = "addressbook") // Shows hibernate the name of the table
@XStreamAlias("item")
public class ItemData {

  @XStreamOmitField // it means to avoid adding this to XML
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE; //?
  @Expose // it means add this to JSON
  @Column (name = "firstname")
  private String firstname;
  @Expose
  @Column (name="middlename")
  private String middlename;
  @Expose
  @Column (name = "lastname")
  private String lastname;
  @Column (name = "home")
  @Type(type = "text")
  private String home;
  @Column (name = "mobile")
  @Type(type = "text")
  private String mobile;
  @Column (name = "work")
  @Type(type = "text")
  private String work;
  @Transient // it means skip it for hibernate
  private String allPhones;
  @Transient
  private String group;
  @Transient
  private String address;
  @Transient
  private String email;
  @Transient
  private String email2;
  @Transient
  private String email3;
  @Transient
  private String allEmails;
  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  @Override
  public String toString() {
    return "ItemData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public ItemData withFName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ItemData withMName(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ItemData withLName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ItemData withHome(String home) {
    this.home = home;
    return this;
  }

  public ItemData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ItemData withWork(String work) {
    this.work = work;
    return this;
  }

  public ItemData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ItemData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ItemData withId(int id) {
    this.id = id;
    return this;
  }

  public ItemData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ItemData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ItemData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ItemData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ItemData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ItemData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getFirstname() {return firstname;}
  public String getMiddlename() {return middlename;}
  public String getLastname() {return lastname;}
  public String getHome() {return home;}
  public String getMobile() {return mobile;}
  public String getWork() {return work;}
  public String getAllPhones() {return allPhones;}
  public String getAllEmails() {return allEmails;}
  public String getGroup() {return group;}
  public int getId() {return id;}
  public String getAddress() {return address;}
  public String getEmail() {return email;}
  public String getEmail2() {return email2;}
  public String getEmail3() {return email3;}
  public File getPhoto() {return new File(photo);}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemData itemData = (ItemData) o;
    return id == itemData.id &&
            Objects.equals(lastname, itemData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname);
  }
}
