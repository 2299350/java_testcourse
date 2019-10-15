package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.Set;

public class ItemModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.item().all().size() == 0) {
      app.item().create(new ItemData().withFName("FName").withMName("MName").withLName("Absence"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testItemModification() throws Exception {

    Set<ItemData> before = app.item().all();
    ItemData modifiedItem = before.iterator().next();

    ItemData item = new ItemData().withFName("FName").withMName("MName").withLName("Edited").withId(modifiedItem.getId());

    app.item().modify(item);

    Set<ItemData> after = app.item().all();

    before.remove(modifiedItem);
    before.add(item);

    Assert.assertEquals(before, after);
  }
}