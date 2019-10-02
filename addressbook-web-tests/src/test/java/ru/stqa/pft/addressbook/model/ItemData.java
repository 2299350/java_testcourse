package ru.stqa.pft.addressbook.model;

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

  public String getMobile() {

    return mobile;
  }

  public String getGroup() {

    return group;
  }
}
