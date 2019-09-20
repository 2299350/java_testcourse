package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemDeletionTests extends TestBase{

  @Test
  public void testItemDeletion() throws Exception {

    app.getItemHelper().selectItem();
    app.getItemHelper().deleteSelectedItems();
  }
}