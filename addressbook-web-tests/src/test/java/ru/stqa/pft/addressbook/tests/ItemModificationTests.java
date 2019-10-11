package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

import java.util.Comparator;
import java.util.List;

public class ItemModificationTests extends TestBase{

  @Test (enabled = false)
  public void testItemModification() throws Exception {

    if (! app.getItemHelper().isThereAnItem()) {
      System.out.println(! app.getItemHelper().isThereAnItem());
      app.getItemHelper().createItem(new ItemData("100500", "6", "7","8","9", "Test3"));
      app.goTo().gotoHomePage();
    }

    List<ItemData> before = app.getItemHelper().getItemList();
    int index = 4;

    app.getItemHelper().initItemModification(index);
    ItemData iData = new ItemData("1edited", "2edited", "Edited","4edited","5edited", null);
    app.getItemHelper().fillItemForm(iData, false);
    app.getItemHelper().submitItemModification();
    app.wd.findElement(By.linkText("home page")).click();

    List<ItemData> after = app.getItemHelper().getItemList();
    before.remove(index - 1);
    before.add(index-1, iData);

    Comparator<? super ItemData> byName = (g1, g2) -> g1.getLastname().compareTo(g2.getLastname());
    before.sort(byName);
    after.sort(byName);

    Assert.assertEquals(before, after);
  }
}