package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("Default"));
    }
    app.goTo().gotoHomePage();
  }

  @DataProvider
  public Iterator<Object[]> validItemsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/items.xml")))) {
      String xml = "";
      String line = reader.readLine();

      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ItemData.class);
      List<ItemData> items = (List<ItemData>) xStream.fromXML(xml);
      return items.stream().map((i) -> new Object[]{i}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validItemsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/items.json")))) {
      String json = "";
      String line = reader.readLine();

      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ItemData> items = gson.fromJson(json, new TypeToken<List<ItemData>>() {
      }.getType()); // List<GroupData>.class
      return items.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validItemsFromJson")
  public void testItemCreation(ItemData item) throws Exception {

    Groups groups = app.db().groups();

    Items before = app.db().items();
    File photo = new File("src/test/resources/stru.jpg");
    GroupData next = groups.iterator().next();
    app.item().create(item.withPhoto(photo).inGroup(next));

    Items after = app.db().items();

    assertThat(after, equalTo(
            before.withAdded(item.withId(after.stream().mapToInt((i) -> i.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File("."); // "." means the current directory
    File photo = new File("src/test/resources/stru.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}