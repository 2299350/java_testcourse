package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemCreationTests extends TestBase{

  @Test
  public void testItemCreation() throws Exception {

    Items before = app.item().all();

    ItemData item = new ItemData()
            .withFName("FName3").withMName("MName3").withLName("LName3")
            .withHome("+79203696563").withMobile("+79203696563").withGroup("Group#1");

    app.item().create(item);

    Set<ItemData> after = app.item().all();

    assertThat(after, equalTo(
            before.withAdded(item.withId(after.stream().mapToInt((i) -> i.getId()).max().getAsInt()))));
  }
}