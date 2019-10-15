package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.Set;

public class ItemDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.item().all().size() == 0) {
      app.item().create(new ItemData().withFName("FName").withMName("MName").withLName("Absence"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testItemDeletion() throws Exception {

    Set<ItemData> before = app.item().all();
    ItemData deletedItem = before.iterator().next();

    app.item().delete(deletedItem);

    Set<ItemData> after = app.item().all();
    before.remove(deletedItem);

    Assert.assertEquals(before,after);
  }
}