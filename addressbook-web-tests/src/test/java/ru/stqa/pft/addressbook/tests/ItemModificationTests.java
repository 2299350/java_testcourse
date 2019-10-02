package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemModificationTests extends TestBase{

  @Test
  public void testItemModification() throws Exception {

    app.getItemHelper().selectItem();
    app.getItemHelper().initItemModification();
    ItemData id = new ItemData("1edited", "2edited", "3edited","4edited","5edited", null);
    app.getItemHelper().fillItemForm(id, false);
    app.getItemHelper().submitItemModification();
  }
}