package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ItemData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemPhoneTests extends TestBase {

  @Test
  public void testItemPhones() {
    app.goTo().gotoHomePage();
    ItemData item = app.item().all().iterator().next();
    ItemData itemInfoFromEditForm = app.item().infoFromEditForm(item);

    assertThat(item.getAllPhones(), equalTo(mergePhones(itemInfoFromEditForm)));
    //assertThat(item.getMobile(), equalTo(cleaned(itemInfoFromEditForm.getMobile())));
    //assertThat(item.getWork(), equalTo(cleaned(itemInfoFromEditForm.getWork())));
  }

  private String mergePhones(ItemData item) {
    return Arrays.asList(item.getHome(), item.getMobile(), item.getWork())
            .stream().filter((s) -> !s.equals("")) // Choose all elements of the stream where the string is not empty
            .map(ItemPhoneTests::cleaned) // apply cleaned() for all the rest elements for that goal The cleaned method has to be static
            .collect(Collectors.joining("\n")); // collect elements divided by delimiter to get the whole string
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("[^+0-9]", "");
  }
}
