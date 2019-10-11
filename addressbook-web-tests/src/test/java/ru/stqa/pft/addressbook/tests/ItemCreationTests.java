package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

import java.util.Comparator;
import java.util.List;

public class ItemCreationTests extends TestBase{

  @Test (enabled = false)
  public void testItemCreation() throws Exception {

    List<ItemData> before = app.getItemHelper().getItemList();

    ItemData item = new ItemData("100500", "6", "7","8","9", "Test3");
    app.getItemHelper().createItem(item);
    app.wd.findElement(By.linkText("home page")).click();

    List<ItemData> after = app.getItemHelper().getItemList();
    before.add(item);

    Comparator<? super ItemData> byName = (g1, g2) -> g1.getFirstname().compareTo(g2.getFirstname());
    before.sort(byName);
    after.sort(byName);

    Assert.assertEquals(before, after);



  }
}