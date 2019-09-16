package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ItemCreationTests {

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
    ItemData itemData = new ItemData("firstname", "middlename", "lastname", "home", "mobile");
    fillGroupForm(itemData);
    submitItemCreation();
    returnToMainPage();
  }

  private void gotoAddPage(String page) {

    wd.findElement(By.linkText(page)).click();
  }

  private void fillGroupForm(ItemData itemData) {

    wd.findElement(By.name(itemData.getFirstname())).click();
    wd.findElement(By.name(itemData.getFirstname())).clear();
    wd.findElement(By.name(itemData.getFirstname())).sendKeys("FN");
    wd.findElement(By.name(itemData.getMiddlename())).click();
    wd.findElement(By.name(itemData.getMiddlename())).clear();
    wd.findElement(By.name(itemData.getMiddlename())).sendKeys("MN");
    wd.findElement(By.name(itemData.getLastname())).click();
    wd.findElement(By.name(itemData.getLastname())).clear();
    wd.findElement(By.name(itemData.getLastname())).sendKeys("LN");
    wd.findElement(By.name(itemData.getHome())).click();
    wd.findElement(By.name(itemData.getHome())).clear();
    wd.findElement(By.name(itemData.getHome())).sendKeys("+79502233650");
    wd.findElement(By.name(itemData.getMobile())).click();
    wd.findElement(By.name(itemData.getMobile())).clear();
    wd.findElement(By.name(itemData.getMobile())).sendKeys("+79502233652");
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
