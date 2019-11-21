package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionHelper extends HelperBase {
  private ApplicationManager app;

  public SessionHelper(ApplicationManager app) {
    super(app);
    this.app = app;
    this.wd = app.getDriver();
  }

  public void loginAdminUI () {
    String loginPage = app.getProperty("web.baseUrl") + "login_page.php";
    String adminLogin = app.getProperty("web.adminLogin");
    String adminPass = app.getProperty("web.adminPassword");

    wd.get(loginPage); //http://localhost:8080/mantisbt-2.22.1/login_page.php
    type(By.cssSelector("input[id='username']"), adminLogin);
    click(By.cssSelector("input[type='submit']"));

    type(By.cssSelector("input[id='password']"), adminPass);
    click(By.cssSelector("input[type='submit']"));
  }

  public String chooseUserAndGetEmail(String userLogin) {
    String userEmail;
    String UsersPage = app.getProperty("web.baseUrl") + "manage_user_page.php";
    wd.get(UsersPage);
    String xpath = String.format("//a[contains(text(), '%s')]", userLogin);
    click(By.xpath(xpath));

    userEmail = wd.findElement(By.cssSelector("input[id='email-field']")).getAttribute("value");
    System.out.println(userEmail);
    click(By.cssSelector("input[value='Reset Password']"));

    return userEmail;
  }

  public void changePass(String confirmationLink, String password) {
    app.getDriver().get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }

  public void loginUserUI (String userLogin, String userPass) {
    String loginPage = app.getProperty("web.baseUrl") + "login_page.php";

    wd.get(loginPage); //http://localhost:8080/mantisbt-2.22.1/login_page.php
    type(By.cssSelector("input[id='username']"), userLogin);
    click(By.cssSelector("input[type='submit']"));

    type(By.cssSelector("input[id='password']"), userPass);
    click(By.cssSelector("input[type='submit']"));
  }
  public boolean isLoggedInAs(String username) throws IOException {
    wd.get(app.getProperty("web.baseUrl") + "/index.php");
    String xpath = String.format("//span[@class='user-info' and contains(text(), '%s')]", username);
    if (!wd.findElement(By.xpath(xpath)).getText().equals(username)) {
      return false;
    }
    return true;
  }

  public void goToUsersPage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  public void goToUserPage() {
    WebElement table = wd.findElement(By.className("table-responsive"));
    List<WebElement> tds = table.findElement(By.tagName("tbody")).findElements(By.tagName("a"));
    tds.get(1).click();
  }

  public String getUserName() {
    return wd.findElement(By.id("edit-username")).getAttribute("value");
  }
}
