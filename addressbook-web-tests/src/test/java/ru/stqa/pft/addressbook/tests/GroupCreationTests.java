package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    GroupData group = new GroupData().withName("CreationName").withHeader("Header2").withFooter("Footer2");

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    app.group().create(group);
    List<GroupData> after = app.group().list();

    Assert.assertEquals(after.size(), before.size() + 1);

    int maxId = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
    group.withId(maxId); // giving the max id to the group
    before.add(group);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}