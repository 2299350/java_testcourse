package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("No groups", "Header1", "Footer1"));
    }

    //int before = app.getGroupHelper().getGroupCount(); // number of groups before modification
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    int id = before.get(before.size() - 1).getId(); // Get Id of the last element of the list
    GroupData group = new GroupData(id,"Edited group", "edited header2", "edited footer2");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().findAndClick(By.linkText("group page"));
    //int after = app.getGroupHelper().getGroupCount(); // number of groups after modification
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(before, after);
    Assert.assertEquals(before.size(), after.size());

    before.remove(before.size()-1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
