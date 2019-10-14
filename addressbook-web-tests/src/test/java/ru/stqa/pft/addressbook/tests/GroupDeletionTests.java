package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

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

    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();

    app.group().delete(deletedGroup);

    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);

    Assert.assertEquals(before,after); // Comparison of lists (List<GroupData>)
  }
}
