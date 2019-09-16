package ru.stqa.pft.addressbook.model;

public class ItemData {

  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String home;
  private final String mobile;

  public ItemData(String firstname, String middlename, String lastname, String home, String mobile) {

    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.home = home;
    this.mobile = mobile;
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
}
