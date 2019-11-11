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
    if (app.db().groups().size() < 2) {
      for (int i = 0; app.db().groups().size() < 2; i++) {
        app.group().create(new GroupData().withName("The group #" + i));
      }
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

    //Adding the item to 2 groups
    ArrayList<String> groupNames = app.group().allGroupNames();
    for (int i = 0; i < 2; i++) {
      app.item().addItemToGroup(anyItem, groupNames.get(i));
      System.out.println("User " + anyItem.getLastname() + " ,id = " + anyItem.getId() + " has been added to the " + groupNames.get(i) + ".");
    }

    Groups itemGroupsAfter = app.item().getAllGroupsOfItemFromDB(anyItem.getId());
    assertThat(itemGroupsAfter.size(), equalTo(2));
  }
}
