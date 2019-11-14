package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class ItemAddingToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
        app.group().create(new GroupData().withName("The group"));
    }

    if (app.db().items().size() == 0) {
      app.item().create(new ItemData().withFName("FName").withMName("MName").withLName("Absence"));
    }
    app.goTo().gotoHomePage();
  }

  @Test
  public void testItemAddingToGroup() throws Exception {

    Items before = app.item().all();
    ItemData anyItem = before.iterator().next();

    //Removing the item from all the groups that it belong to
    Groups itemGroups = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    for (GroupData g : itemGroups) {
      app.item().removeItemFromGroup(anyItem, g.getName());
    }
    Groups itemGroupsBefore = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    assertThat(itemGroupsBefore.size(), equalTo(0));

    //Adding the item to any group
    String groupId = app.group().anyGroupId();

    app.item().addItemToGroup(anyItem, groupId);
    System.out.println("User " + anyItem.getLastname() + " ,id = " + anyItem.getId() + " has been added to the " + groupId + ".");

    assertTrue(app.item().isItemBelongToGroup(anyItem, Integer.parseInt(groupId)));

    Groups itemGroupsAfter = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    assertThat(itemGroupsAfter.size(), equalTo(1));
    assertThat(Integer.parseInt(groupId), equalTo(itemGroupsAfter.stream().map((g) -> g.getId()).findFirst().get()));
  }
}
