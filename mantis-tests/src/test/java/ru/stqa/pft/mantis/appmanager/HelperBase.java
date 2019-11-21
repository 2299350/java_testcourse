package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;

import java.io.File;

public class HelperBase {

  protected WebDriver wd;
  protected ApplicationManager app;

  public HelperBase(ApplicationManager app) {
    this.wd = app.wd;
    this.app = app;
  }

  protected void click(By locator) {
    try {
      app.getDriver().findElement(locator).click();
    } catch (NoSuchElementException ex) {
      System.out.println("There isn't such an element on the page | HelperBase");
    }
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = app.getDriver().findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        app.getDriver().findElement(locator).clear();
        app.getDriver().findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By locator, File file) {
    if (file != null) {
      app.getDriver().findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  private boolean isAlertPresent() {

    try {
      app.getDriver().switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  protected boolean isElementPresent(By locator) {

    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
