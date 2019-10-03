package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Header1", "Footer1"));
    }

    //int before = app.getGroupHelper().getGroupCount(); // number of groups before deletion
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    //int after = app.getGroupHelper().getGroupCount(); // number of groups after deletion
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after, before - 1);
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
/*    for (int i = 0; i < after.size(); i++) { // Comparison of each element
      Assert.assertEquals(before.get(i), after.get(i));
    }*/
    Assert.assertEquals(before,after); // Comparison of lists (List<GroupData>)
  }
}
