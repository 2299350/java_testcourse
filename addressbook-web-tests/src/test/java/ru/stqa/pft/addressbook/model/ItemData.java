package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ItemData {

  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String home;
  private final String mobile;
  private String group;

  public ItemData(String firstname, String middlename, String lastname, String home, String mobile, String group) {

    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.home = home;
    this.mobile = mobile;
    this.group = group;
  }

  public ItemData(String firstname, String lastname) {

    this.firstname = firstname;
    this.middlename = null;
    this.lastname = lastname;
    this.home = null;
    this.mobile = null;
    this.group = "ItemHelper";
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {

    return middlename;
  }

  public String getLastname() {

    return lastname;
  }

  public String getHome() {

    return home;
  }

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

  public String getMobile() {

    return mobile;
  }

  public String getGroup() {

    return group;
  }
}
