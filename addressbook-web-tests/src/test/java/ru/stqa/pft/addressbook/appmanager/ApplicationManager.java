package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  private final Properties properties;
  public WebDriver wd;

  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ItemHelper itemHelper;
  private SessionHelper sessionHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {

    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

    if ("".equals(properties.getProperty("selenium.server"))){
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
    } else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win7")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));

    groupHelper = new GroupHelper(this);
    navigationHelper = new NavigationHelper(this);
    sessionHelper = new SessionHelper(this);
    itemHelper = new ItemHelper(this);


    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {

    sessionHelper.logout();
    wd.quit();
  }

  public GroupHelper group() {

    return groupHelper;
  }

  public ItemHelper item() {

    return itemHelper;
  }

  public NavigationHelper goTo() {

    return navigationHelper;
  }

  public DbHelper db() {

    return dbHelper;
  }

  public String getProperty(String key) {

    return properties.getProperty(key);
  }
}
