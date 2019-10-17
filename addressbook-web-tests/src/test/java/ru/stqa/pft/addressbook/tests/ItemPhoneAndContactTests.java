package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ItemData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemPhoneAndContactTests extends TestBase {

  @Test
  public void testItemPhonesAndContacts() {
    app.goTo().gotoHomePage();
    ItemData item = app.item().all().iterator().next();
    ItemData itemInfoFromEditForm = app.item().infoFromEditForm(item);

    assertThat(item.getAllPhones(), equalTo(mergePhones(itemInfoFromEditForm)));
    assertThat(item.getAllEmails(), equalTo(mergeEmails(itemInfoFromEditForm)));
    assertThat(item.getAddress(), equalTo(itemInfoFromEditForm.getAddress()));
  }

  private String mergePhones(ItemData item) {
    return Arrays.asList(item.getHome(), item.getMobile(), item.getWork())
            .stream().filter((s) -> !s.equals("")) // Choose all elements of the stream where the string is not empty
            .map(ItemPhoneAndContactTests::cleaned) // apply cleaned() for all the rest elements for that goal The cleaned method has to be static
            .collect(Collectors.joining("\n")); // collect elements divided by the delimiter to get the whole string
  }

  private String mergeEmails(ItemData item) {
    String ae = Arrays.asList(item.getEmail(), item.getEmail2(), item.getEmail3())
            .stream().filter((s) -> !s.equals("")) // Choose all elements of the stream where the string is not empty
            .collect(Collectors.joining("\n")); // collect elements divided by the delimiter to get the whole string
    return ae;
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("[^+0-9]", "");
  }
}
