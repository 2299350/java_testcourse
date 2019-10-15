package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.Comparator;
import java.util.List;

public class ItemCreationTests extends TestBase{

  @Test
  public void testItemCreation() throws Exception {

    List<ItemData> before = app.item().list();

    ItemData item = new ItemData("100500", "6", "LastName","8","9", "Test3");

    app.item().create(item);

    List<ItemData> after = app.item().list();
    before.add(item);

    Comparator<? super ItemData> byName = (g1, g2) -> g1.getFirstname().compareTo(g2.getFirstname());
    before.sort(byName);
    after.sort(byName);

    Assert.assertEquals(before, after);



  }
}