package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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

    Items before = app.item().all();
    ItemData modifiedItem = before.iterator().next();

    ItemData item = new ItemData().withFName("FName").withMName("MName").withLName("Edited").withId(modifiedItem.getId());

    app.item().modify(item);

    Items after = app.item().all();

    assertThat(after, equalTo(before.without(modifiedItem).withAdded(item)));
  }
}