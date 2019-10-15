package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.Comparator;
import java.util.List;

public class ItemModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.item().list().size() == 0) {
      app.item().create(new ItemData("100500", "6", "7","8","9", "Test3"));
      app.goTo().gotoHomePage();
    }
  }

  @Test
  public void testItemModification() throws Exception {

    List<ItemData> before = app.item().list();
    int index = before.size();

    ItemData iData = new ItemData("1edited", "2edited", "Edited","4edited","5edited", null);

    app.item().modify(index, iData);

    List<ItemData> after = app.item().list();

    before.remove(index - 1);
    before.add(index-1, iData);

    Comparator<? super ItemData> byName = (g1, g2) -> g1.getLastname().compareTo(g2.getLastname());
    before.sort(byName);
    after.sort(byName);

    Assert.assertEquals(before, after);
  }
}