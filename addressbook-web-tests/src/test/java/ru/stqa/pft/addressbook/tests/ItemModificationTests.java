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
      app.getNavigationHelper().gotoHomePage();
    }

    List<ItemData> before = app.getItemHelper().getItemList();
    int index = 4; //use 0 for the last element

    app.getItemHelper().initItemModification(index);
    ItemData id = new ItemData("1edited", "2edited", "Edited","4edited","5edited", null);
    app.getItemHelper().fillItemForm(id, false);
    app.getItemHelper().submitItemModification();
    app.wd.findElement(By.linkText("home page")).click();

    List<ItemData> after = app.getItemHelper().getItemList();
    if (index == 0) {
      before.remove(before.size() - 1);
      before.add(id);
    } else {
      before.remove(index - 1);
      before.add(index-1, id);
    }

    Comparator<? super ItemData> byName = (g1, g2) -> g1.getLastname().compareTo(g2.getLastname());
    before.sort(byName);
    after.sort(byName);

    Assert.assertEquals(before, after);
  }
}