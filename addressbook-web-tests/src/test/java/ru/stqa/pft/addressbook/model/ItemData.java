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

  public String getFirstname() {return firstname;}

  public String getMiddlename() {return middlename;}

  public String getLastname() {return lastname;}

  public String getHome() {return home;}

  public String getMobile() {return mobile;}

  public String getWork() {return work;}

  public String getAllPhones() {return allPhones;}

  public String getGroup() {return group;}

  public int getId() {return id;}

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
