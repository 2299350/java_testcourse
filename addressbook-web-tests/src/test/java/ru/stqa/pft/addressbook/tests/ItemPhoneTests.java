package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ItemData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemPhoneTests extends TestBase {

  @Test
  public void testItemPhones() {
    app.goTo().gotoHomePage();
    ItemData item = app.item().all().iterator().next();
    ItemData itemInfoFromEditForm = app.item().infoFromEditForm(item);

    assertThat(item.getHome(), equalTo(cleaned(itemInfoFromEditForm.getHome())));
    assertThat(item.getMobile(), equalTo(cleaned(itemInfoFromEditForm.getMobile())));
    assertThat(item.getWork(), equalTo(cleaned(itemInfoFromEditForm.getWork())));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("[^+0-9]", "");
  }
}
