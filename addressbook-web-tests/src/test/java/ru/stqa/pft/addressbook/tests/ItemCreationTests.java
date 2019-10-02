package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemCreationTests extends TestBase{

  @Test
  public void testItemCreation() throws Exception {

    app.getItemHelper().initItemCreation();
    ItemData id = new ItemData("100500", "6", "7","8","9", "Test3");
    app.getItemHelper().fillItemForm(id, true);
    app.getItemHelper().submitItemCreation();
  }
}