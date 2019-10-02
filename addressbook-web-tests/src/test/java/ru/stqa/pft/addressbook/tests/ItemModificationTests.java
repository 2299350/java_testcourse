package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemModificationTests extends TestBase{

  @Test
  public void testItemModification() throws Exception {

    if (! app.getItemHelper().isThereAnItem()) {
      System.out.println(! app.getItemHelper().isThereAnItem());
      app.getItemHelper().createItem(new ItemData("100500", "6", "7","8","9", "Test3"));
      app.getNavigationHelper().gotoHomePage();
    }

    app.getItemHelper().selectItem();
    app.getItemHelper().initItemModification();
    ItemData id = new ItemData("1edited", "2edited", "3edited","4edited","5edited", null);
    app.getItemHelper().fillItemForm(id, false);
    app.getItemHelper().submitItemModification();
  }
}