package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test1", "Header1", "Footer1"));
    }

    //int before = app.getGroupHelper().getGroupCount(); // number of groups before modification
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("Test500", "edited header2", "edited footer2"));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().findAndClick(By.linkText("group page"));
    //int after = app.getGroupHelper().getGroupCount(); // number of groups after modification
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(before, after);
    Assert.assertEquals(before.size(), after.size());
  }
}
