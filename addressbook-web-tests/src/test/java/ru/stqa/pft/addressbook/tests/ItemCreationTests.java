package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import java.io.File;
import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemCreationTests extends TestBase{

  @Test
  public void testItemCreation() throws Exception {

    Items before = app.item().all();

    File photo = new File("src/test/resources/stru.jpg");
    ItemData item = new ItemData()
            .withFName("FName3").withMName("MName3").withLName("LName3")
            .withHome("+7(920)3696563").withMobile("+7-920-369-65-65").withWork("+7 920 369 65 67").withPhoto(photo);

    app.item().create(item);

    Set<ItemData> after = app.item().all();

    assertThat(after, equalTo(
            before.withAdded(item.withId(after.stream().mapToInt((i) -> i.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File ("."); // "." means the current directory
    File photo = new File("src/test/resources/stru.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}