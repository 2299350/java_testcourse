package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.*;

public class ItemHelper extends HelperBase {

  public ItemHelper(WebDriver wd) {

    super(wd);
  }

  public void submitItemCreation() {

    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void submitItemModification() {

    wd.findElement(By.xpath("(//input[@name='update'])[2]")).click();
  }

  public void fillItemForm(ItemData itemData, boolean creation) {

    type(By.name("firstname"), itemData.getFirstname());
    type(By.name("middlename"), itemData.getMiddlename());
    type(By.name("lastname"), itemData.getLastname());
    type(By.name("home"), itemData.getHome());
    type(By.name("mobile"), itemData.getMobile());
    if (!creation){
      fillSelector("bday","1");
      fillSelector("bmonth","January");
      type(By.name("byear"), "1990");
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    } else {
      fillSelector("new_group","Edited group");
    }
  }

  private void fillSelector(String sName, String value) {

    wd.findElement(By.name(sName)).click();
    new Select(wd.findElement(By.name(sName))).selectByVisibleText(value);
    wd.findElement(By.name(sName)).click();
  }

  public void selectItem(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectItemById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initItemModificationById(int id) {
      click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
  }

  public void deleteSelectedItems() {

    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept(); // confirmation pop-up
  }

  public void initItemCreation() {

    click(By.linkText("add new"));
  }

  public void create(ItemData item) {
    initItemCreation();
    fillItemForm(item, true);
    submitItemCreation();
    wd.findElement(By.linkText("home page")).click();
  }

  public void modify(ItemData item) {
    initItemModificationById(item.getId());
    fillItemForm(item, false);
    submitItemModification();
    click(By.linkText("home page"));
  }

  public void delete(int index) {
    selectItem(index);
    deleteSelectedItems();
    wd.findElement(By.linkText("home")).click();
  }

  public void delete(ItemData item) {
    selectItemById(item.getId());
    deleteSelectedItems();
    wd.findElement(By.linkText("home")).click();
  }

  public boolean isThereAnItem() {
    return isElementPresent(By.xpath("(//img[@alt='Edit'])[last()]"));
  }

  public Set<ItemData> all() {

    Set<ItemData> items = new HashSet<ItemData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr"));
    elements.remove(0);

    for (WebElement e : elements) {
      String firstname = e.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = e.findElement(By.cssSelector("td:nth-child(2)")).getText();

      String link = e.findElement(By.cssSelector("td:nth-child(8) > a")).getAttribute("href"); // get link
      int id = Integer. parseInt(link.substring(link.lastIndexOf("=") + 1)); // get id from link

      ItemData item = new ItemData().withFName(firstname).withLName(lastname).withId(id);
      items.add(item);
    }
    return items;
  }
}
