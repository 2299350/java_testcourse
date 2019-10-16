package ru.stqa.pft.addressbook.model;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactData extends TestBase {

  @Test
  public void testContactPhones() {
    app.goTo().gotoHomePage();
    ItemData item = app.item().all().iterator().next();
    ItemData contactInfoFromEditForm = app.item().infoFromEditForm(item);
  }
}
