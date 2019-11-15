package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{


  public SessionHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {

    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("user"), username);
    type(By.name("pass"), password);
    click(By.xpath("//form[@id='LoginForm']/input[3]"));
  }

  public void logout() {

    wd.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    //wd.findElement(By.linkText("Logout")).click();
  }
}


