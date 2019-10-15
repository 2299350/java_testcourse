package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ItemData {

  private String firstname;
  private String middlename;
  private String lastname;
  private String home;
  private String mobile;
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

  public ItemData withGroup(String group) {
    this.group = group;
    return this;
  }

  public String getFirstname() {return firstname;}

  public String getMiddlename() {return middlename;}

  public String getLastname() {return lastname;}

  public String getHome() {return home;}

  public String getMobile() {return mobile;}

  public String getGroup() {return group;}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemData itemData = (ItemData) o;
    return Objects.equals(firstname, itemData.firstname) &&
            Objects.equals(lastname, itemData.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }
}
