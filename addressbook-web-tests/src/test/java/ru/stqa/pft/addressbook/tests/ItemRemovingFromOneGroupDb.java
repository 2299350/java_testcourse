package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


public class ItemRemovingFromOneGroupDb extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().items().size() == 0) {
      app.item().create(new ItemData().withFName("FName").withMName("MName").withLName("Absence"));
    }
    app.goTo().gotoHomePage();
  }

  @Test
  public void testItemRemovingFromOneGroup() throws Exception {

    Items before = app.item().all();
    ItemData anyItem = before.iterator().next();
    Groups itemGroups = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    GroupData group;

    if (itemGroups.size() == 0) {
      Groups allGroups = app.db().groups();
      if (allGroups.size() == 0) {
        group = new GroupData().withName("Group for Item Removing");
        app.group().create(group);
      } else {
        group = allGroups.iterator().next();
      }

      app.item().addItemToGroup(anyItem, group.getName());
      Groups itemGroupsAdded = app.item().getAllGroupsOfItemFromDB(anyItem.getId());

      assertThat(itemGroupsAdded.size(), greaterThan(0));
    }

    itemGroups = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    int groupsNumBefore = itemGroups.size();

    app.item().removeItemFromGroup(anyItem, itemGroups.iterator().next().getName());

    Groups itemGroupsAfter = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    int groupsNumAfter = itemGroupsAfter.size();

    assertThat(groupsNumAfter, equalTo(groupsNumBefore - 1));
  }
}






