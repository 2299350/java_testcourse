package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ItemCreationTests extends TestBase {

  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {

    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    login("admin", "secret");
  }

  private void login(String username, String password) {

    wd.get("http://localhost:8080/");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {

    gotoAddPage("add new");
    ItemData itemData = new ItemData("FN", "MN", "LN", "+79502233650", "+79502233652");
    fillGroupForm(itemData);
    submitItemCreation();
    returnToMainPage();
    wd.findElement(By.linkText("Logout")).click();
  }

  private void gotoAddPage(String page) {

    wd.findElement(By.linkText(page)).click();
  }

  private void fillGroupForm(ItemData itemData) {

    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(itemData.getFirstname());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(itemData.getMiddlename());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(itemData.getLastname());
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(itemData.getHome());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(itemData.getMobile());
  }

  private void returnToMainPage() {
    wd.get("http://localhost:8080/");
  }

  public void submitItemCreation() {

    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {

    wd.quit();
  }

  private boolean isElementPresent(By by) {

    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {

    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
