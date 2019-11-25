package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      GroupData noGroups = new GroupData().withName("No groups").withHeader("Header1").withFooter("Footer1");
      app.group().create(noGroups);
    }
  }

  @Test
  public void testGroupModification() {

    Groups before = app.db().groups();
    GroupData modifiedGroup = before.iterator().next(); // random group selection

    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("Edited group2").withHeader("edited header2").withFooter("edited footer2");
    app.goTo().groupPage();
    app.group().modify(group);

    assertEquals(app.group().count(), before.size()); // Not necessary

    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }


}
