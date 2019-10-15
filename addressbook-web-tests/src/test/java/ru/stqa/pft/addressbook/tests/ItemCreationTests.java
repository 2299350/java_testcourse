package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.Set;

public class ItemCreationTests extends TestBase{

  @Test
  public void testItemCreation() throws Exception {

    Set<ItemData> before = app.item().all();

    ItemData item = new ItemData()
            .withFName("FName3").withMName("MName3").withLName("LName3")
            .withHome("+79203696563").withMobile("+79203696563").withGroup("Group#1");

    app.item().create(item);

    Set<ItemData> after = app.item().all();
    item.withId(after.stream().mapToInt((i) -> i.getId()).max().getAsInt());
    before.add(item);

    Assert.assertEquals(before, after);



  }
}