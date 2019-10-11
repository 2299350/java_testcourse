package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    GroupData noGroups = new GroupData().withName("No groups").withHeader("Header1").withFooter("Footer1");
    if (app.group().list().size() == 0) {
      app.group().create(noGroups);
    }
  }

  @Test
  public void testGroupModification() {

    List<GroupData> before = app.group().list();

    int index = before.size() - 1;
    int id = before.get(index).getId(); // Get Id of the n-element of the list
    GroupData editingGroup = new GroupData()
            .withId(id).withName("Edited group").withHeader("edited header2").withFooter("edited footer2");

    app.group().modify(index, editingGroup);

    List<GroupData> after = app.group().list();

    Assert.assertEquals(before.size(), after.size());

    before.remove(index);
    before.add(editingGroup);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before,after);
  }
}
