package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;

public class ItemCreationTests extends TestBase{

  @Test
  public void testItemCreation() throws Exception {

    app.gotoThePage("add new");
    ItemData id = new ItemData("1f", "2f", "3f","4f","5f");
    app.getItemHelper().fillItemForm(id);
    app.getItemHelper().submitItemCreation();
  }
}