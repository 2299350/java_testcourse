package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

  private String userName;
  private String userEmail;
  private String newPassword;

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    userName = "user13";
    newPassword = "newPassword";

    app.session().loginAdminUI(); // Login as an admin
    userEmail = app.session().chooseUserAndGetEmail(userName); // Init password change and get user's email

    List<MailMessage> mailMessages = app.mail().waitForMail(1,10000); // Get confirmation link
    String confirmationLink = findConfirmationLink(mailMessages, userEmail);

    app.session().changePass(confirmationLink, newPassword); // Change the pass

    app.session().loginUserUI(userName, newPassword); // Login with a new pass
    Assert.assertTrue(app.session().isLoggedInAs(userName)); // Check login with new credentials
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
