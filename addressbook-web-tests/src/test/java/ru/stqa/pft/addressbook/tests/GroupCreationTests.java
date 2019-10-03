package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    //int before = app.getGroupHelper().getGroupCount(); // number of groups before adding
    app.getGroupHelper().createGroup(new GroupData("Test2", "Header2", "Footer2"));
    //int after = app.getGroupHelper().getGroupCount(); // number of groups after adding
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after, before + 1);
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}