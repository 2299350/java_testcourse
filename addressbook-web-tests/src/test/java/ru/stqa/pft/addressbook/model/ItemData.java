package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ItemData {

  private int id = Integer.MAX_VALUE; //?
  private String firstname;
  private String middlename;
  private String lastname;
  private String home;
  private String mobile;
  private String work;
  private String allPhones;
  private String group;
  private String address;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;


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
