package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    GroupData noGroups = new GroupData().withName("No groups").withHeader("Header1").withFooter("Footer1");
    if (app.group().list().size() == 0) {
      app.group().create(noGroups);
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {

    List<GroupData> before = app.group().list();
    int index = before.size()-1;

    app.group().delete(index);

    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);

    Assert.assertEquals(before,after); // Comparison of lists (List<GroupData>)
  }
}
