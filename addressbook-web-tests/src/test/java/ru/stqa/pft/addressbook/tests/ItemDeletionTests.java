package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemDeletionTests extends TestBase{

  @Test
  public void testItemDeletion() throws Exception {

    if (! app.getItemHelper().isThereAnItem()) {
      app.getItemHelper().createItem(new ItemData("100500", "6", "7","8","9", "Test3"));
      app.getNavigationHelper().gotoHomePage();
    }

    app.getItemHelper().selectItem();
    app.getItemHelper().deleteSelectedItems();
  }
}