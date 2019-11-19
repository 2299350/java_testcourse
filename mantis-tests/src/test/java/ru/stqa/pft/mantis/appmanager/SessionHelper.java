package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

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

  public void chooseUser (String userLogin) {
    String userEmail;
    String UsersPage = app.getProperty("web.baseUrl") + "manage_user_page.php";
    wd.get(UsersPage);
    String xpath = String.format("//a[contains(text(), '%s')]", userLogin);
    click(By.xpath(xpath));

    userEmail = wd.findElement(By.cssSelector("input[id='email-field']")).getAttribute("value");
    System.out.println(userEmail);
  }
}
