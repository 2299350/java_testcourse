package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import java.util.ArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    String groupName = app.group().anyGroupName();

    app.item().addItemToGroup(anyItem, groupName);
    System.out.println("User " + anyItem.getLastname() + " ,id = " + anyItem.getId() + " has been added to the " + groupName + ".");


    Groups itemGroupsAfter = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    assertThat(itemGroupsAfter.size(), equalTo(1));
  }
}
