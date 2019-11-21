package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  // add  -Dbrowser=firefox to Run/Debug Configuration to convey browser type | BrowserType.CHROME will be used by default

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.bak");
  }

  public boolean isIssueOpened(int issueId) {
    try {
      return app.soap().isIssueOpened(issueId);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpened(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  @AfterSuite
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak", "config_inc.php");
    app.stop();
  }
}
