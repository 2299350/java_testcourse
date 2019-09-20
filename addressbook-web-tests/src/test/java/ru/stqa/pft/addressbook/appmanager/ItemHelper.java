package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemHelper extends HelperBase {

  public ItemHelper(FirefoxDriver wd) {

    super(wd);
  }

  public void submitItemCreation() {

    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void submitItemModification() {

    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void fillItemForm(ItemData itemData) {

    type(By.name("firstname"), itemData.getFirstname());
    type(By.name("middlename"), itemData.getMiddlename());
    type(By.name("lastname"), itemData.getLastname());
    type(By.name("home"), itemData.getHome());
    type(By.name("mobile"), itemData.getMobile());
  }

  public void selectItem() {

    click(By.name("selected[]"));
  }

  public void initItemModification() {

    click(By.xpath("//img[@alt='Edit']"));
  }

  public void deleteSelectedItems() {

    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept(); // confirmation pop-up
  }

  public void goToMainPage() {

    wd.get("http://localhost:8080/");
  }
}
