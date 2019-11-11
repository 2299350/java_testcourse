package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ItemRemovingFromAllGroupsDb extends TestBase {

  @Test
  public void testItemRemovingFromAllGroups() throws Exception {

    Items before = app.item().all();
    ItemData anyItem = before.iterator().next();
    Groups itemGroups = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    GroupData group;

    if (itemGroups.size() == 0) {
      Groups allGroups = app.db().groups();
      if (allGroups.size() == 0) {
        group = new GroupData().withName("Group for Item Removing");
      } else {
        group = allGroups.iterator().next();
      }

      app.group().create(group);

      app.item().addItemToGroup(anyItem, group.getName());
    }

    for (GroupData g : itemGroups) {
      app.item().removeItemFromGroup(anyItem, g.getName());
    }
    //Items after = app.db().items();
    //assertThat(after, equalTo(before));
  }



}






