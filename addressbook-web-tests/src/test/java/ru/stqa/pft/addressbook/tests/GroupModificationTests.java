package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    GroupData noGroups = new GroupData().withName("No groups").withHeader("Header1").withFooter("Footer1");
    if (app.group().all().size() == 0) {
      app.group().create(noGroups);
    }
  }

  @Test
  public void testGroupModification() {

    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next(); // random group selection

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("Edited group").withHeader("edited header2").withFooter("edited footer2");

    app.group().modify(group);

    Set<GroupData> after = app.group().all();

    Assert.assertEquals(before.size(), after.size());

    before.remove(modifiedGroup);
    before.add(group);

    Assert.assertEquals(before,after);
  }
}
