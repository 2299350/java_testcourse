package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //int before = app.getGroupHelper().getGroupCount(); // number of groups before adding
    GroupData group = new GroupData("Test2", "Header2", "Footer2");
    app.getGroupHelper().createGroup(group);
    //int after = app.getGroupHelper().getGroupCount(); // number of groups after adding
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after, before + 1);
    Assert.assertEquals(after.size(), before.size() + 1);

    // Finding of the max id in List<GroupData> after
    int max = 0;
    for (GroupData g : after) {
      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max); // giving the max id to the group
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}