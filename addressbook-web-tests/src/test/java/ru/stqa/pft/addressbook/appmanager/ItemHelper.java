package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import java.sql.*;
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
    type(By.name("work"), itemData.getWork());
    type(By.name("address"), itemData.getAddress());
    type(By.name("email"), itemData.getEmail());
    type(By.name("email2"), itemData.getEmail2());
    type(By.name("email3"), itemData.getEmail3());
    attach(By.name("photo"), itemData.getPhoto());
    if (!creation){
      fillSelector("bday","1");
      fillSelector("bmonth","January");
      type(By.name("byear"), "1990");
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    } else {
      fillSelector("new_group","Default");
    }
  }

  public void addItemToGroup(ItemData item, String groupName) {
    wd.get("http://localhost:8080");
    wd.findElement(By.id(Integer.toString(item.getId()))).click();
    wd.findElement(By.name("to_group")).click();
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    wd.findElement(By.name("to_group")).click();
    wd.findElement(By.name("add")).click();
    wd.get("http://localhost:8080");
  }

  public void removeItemFromGroup(ItemData item, String groupName) {

    wd.get("http://localhost:8080/");
    wd.findElement(By.name("group")).click();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    wd.findElement(By.name("group")).click();
    wd.findElement(By.id(Integer.toString(item.getId()))).click();
    wd.findElement(By.name("remove")).click();
  }

  private void fillSelector(String sName, String value) {

    wd.findElement(By.name(sName)).click();
    new Select(wd.findElement(By.name(sName))).selectByVisibleText("[none]");
    wd.findElement(By.name(sName)).click();
  }

  public void selectItem(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectItemById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initItemModificationById(int id) {

    click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    //click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
  }

  public void deleteSelectedItems() {

    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept(); // confirmation pop-up
  }

  public void initItemCreation() {

    click(By.linkText("add new"));
  }

  public Groups getAllGroupsOfItemFromDB(int id) {

    Groups allGroupsOfItem = new Groups();
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
      PreparedStatement st = conn.prepareStatement("SELECT group_list.group_name, group_list.group_id, group_list.group_header, group_list.group_footer " +
              "FROM group_list " +
              "LEFT JOIN address_in_groups " +
              "ON group_list.group_id = address_in_groups.group_id " +
              "LEFT JOIN addressbook " +
              "ON address_in_groups.id = addressbook.id " +
              "WHERE addressbook.id = ? AND group_list.deprecated = '0000-00-00';");
      st.setInt(1, id);
      ResultSet rs = st.executeQuery();

      while (rs.next()) {
        allGroupsOfItem.add(new GroupData().withId(rs.getInt("group_id"))
                .withName(rs.getString("group_name")).withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
      }

      rs.close(); //ResultSet
      st.close(); // Statement
      conn.close(); // Connection

      System.out.println(allGroupsOfItem);

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return allGroupsOfItem;
  }

  public void create(ItemData item) {
    initItemCreation();
    fillItemForm(item, true);
    submitItemCreation();
    itemCache = null;
    wd.findElement(By.linkText("home page")).click();
  }

  public void modify(ItemData item) {
    initItemModificationById(item.getId());
    fillItemForm(item, false);
    submitItemModification();
    itemCache = null;
    click(By.linkText("home page"));
  }

  public void delete(ItemData item) {
    selectItemById(item.getId());
    deleteSelectedItems();
    itemCache = null;
    wd.findElement(By.linkText("home")).click();
  }

  public boolean isThereAnItem() {
    return isElementPresent(By.xpath("(//img[@alt='Edit'])[last()]"));
  }

  private Items itemCache = null;

  public Items all() {
    if (itemCache != null) {
      return new Items(itemCache); // return a itemCache copy
    }
    itemCache = new Items();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable tr"));
    elements.remove(0);

    for (WebElement e : elements) {
      String firstname = e.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = e.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String address = e.findElement(By.cssSelector("td:nth-child(4)")).getText();

      String allPhones = e.findElement(By.cssSelector("td:nth-child(6)")).getText();
      //String[] phones = allPhones.split("\n");
      String allEmails = e.findElement(By.cssSelector("td:nth-child(5)")).getText();

      String link = e.findElement(By.cssSelector("td:nth-child(8) > a")).getAttribute("href"); // get link
      int id = Integer. parseInt(link.substring(link.lastIndexOf("=") + 1)); // get id from link

      ItemData item = new ItemData().withFName(firstname).withLName(lastname).withId(id)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
              //.withHome(phones[0]).withMobile(phones[1]).withWork(phones[2]);

      itemCache.add(item);
    }
    return new Items(itemCache); // return a itemCache copy;
  }

  public ItemData infoFromEditForm (ItemData item) {
    initItemModificationById(item.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ItemData()
            .withId(item.getId()).withFName(firstname).withLName(lastname).withHome(home).withMobile(mobile).withWork(work)
            .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
