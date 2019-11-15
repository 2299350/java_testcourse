package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import java.io.IOException;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) throws IOException {
    super(app);
  }

  public void start(String username, String email) {

    String fullUrl = app.getProperty("web.baseUrl") + "signup_page.php";
    app.getDriver().get(fullUrl);
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    app.getDriver().get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("input[type='submit']"));
  }
}
