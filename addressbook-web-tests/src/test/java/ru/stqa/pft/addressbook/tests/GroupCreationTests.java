package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    GroupData group = new GroupData().withName("CreationName").withHeader("Header2").withFooter("Footer2");

    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    app.group().create(group);
    Set<GroupData> after = app.group().all();

    Assert.assertEquals(after.size(), before.size() + 1);

    int a = after.stream().mapToInt((g) -> g.getId()).max().getAsInt();
    group.withId(a);
    before.add(group);

    Assert.assertEquals(before, after);
  }
}