package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().items().size() == 0) {
      app.item().create(new ItemData().withFName("FName").withMName("MName").withLName("Absence"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testItemDeletion() throws Exception {

    Items before = app.db().items();
    ItemData deletedItem = before.iterator().next();

    app.item().delete(deletedItem);

    Items after = app.db().items();

    assertThat(after, equalTo(before.without(deletedItem)));
  }
}