package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;


public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    GroupData noGroups = new GroupData().withName("No groups").withHeader("Header1").withFooter("Footer1");
    if (app.group().all().size() == 0) {
      app.group().create(noGroups);
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);

    Assert.assertEquals(app.group().count(), before.size() - 1);

    Groups after = app.db().groups();

    before.remove(deletedGroup);
    assertThat(after, equalTo(before.without(deletedGroup)));
  }
}
