package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class ItemDeletionTests extends TestBase{

  @Test
  public void testItemDeletion() throws Exception {

    app.getItemHelper().selectItem();
    app.getItemHelper().deleteSelectedItems();
  }
}