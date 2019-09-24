package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  public FirefoxDriver wd;

  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ItemHelper itemHelper;
  private SessionHelper sessionHelper;

  public void init() {

    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    itemHelper = new ItemHelper(wd);

    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    sessionHelper.logout();
    wd.quit();
  }

  public GroupHelper getGroupHelper() {

    return groupHelper;
  }

  public ItemHelper getItemHelper() {

    return itemHelper;
  }

  public NavigationHelper getNavigationHelper() {

    return navigationHelper;
  }

  public void gotoGroupsPage() {

    navigationHelper.gotoThePage("http://localhost:8080/group.php");
  }
}
