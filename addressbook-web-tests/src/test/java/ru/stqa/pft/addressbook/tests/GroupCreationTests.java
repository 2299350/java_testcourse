package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //int before = app.getGroupHelper().getGroupCount(); // number of groups before adding
    GroupData group = new GroupData("TestLambda", "Header2", "Footer2");
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

/*    Comparator<? super GroupData> byId = new Comparator<GroupData>() {
      @Override
      public int compare(GroupData o1, GroupData o2) {
        return Integer.compare(o1.getId(), o2.getId());
      }
    };*/

    Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());

    after.stream().max(byId).get().getId();

    group.setId(max); // giving the max id to the group
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}