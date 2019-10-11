package ru.stqa.pft.addressbook.tests;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

import java.util.List;

public class ItemDeletionTests extends TestBase{

  @Test (enabled = false)
  public void testItemDeletion() throws Exception {

    if (! app.getItemHelper().isThereAnItem()) {
      app.getItemHelper().createItem(new ItemData("100500", "6", "7","8","9", "Test3"));
      app.getNavigationHelper().gotoHomePage();
    }

    List<ItemData> before = app.getItemHelper().getItemList();

    app.getItemHelper().selectItem(before.size()-1);
    app.getItemHelper().deleteSelectedItems();
    app.wd.findElement(By.linkText("home")).click();


    List<ItemData> after = app.getItemHelper().getItemList();
    before.remove(before.size() - 1);

    Assert.assertEquals(before,after);
  }
}