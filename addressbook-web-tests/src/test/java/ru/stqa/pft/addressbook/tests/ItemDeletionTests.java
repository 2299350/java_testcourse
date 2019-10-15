package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.List;

public class ItemDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.item().list().size() == 0) {
      app.item().create(new ItemData().withFName("FName").withMName("MName").withLName("Absence"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testItemDeletion() throws Exception {

    List<ItemData> before = app.item().list();
    int index = before.size() - 1;

    app.item().delete(index);

    List<ItemData> after = app.item().list();
    before.remove(index);

    Assert.assertEquals(before,after);
  }
}