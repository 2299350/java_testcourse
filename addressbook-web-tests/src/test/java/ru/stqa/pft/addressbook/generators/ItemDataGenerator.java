package ru.stqa.pft.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ItemData;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;

public class ItemDataGenerator {

  @Parameter (names = "-c", description = "Item count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {

    ItemDataGenerator generator = new ItemDataGenerator();
    JCommander jCommander = new JCommander(generator); // Edit configuration | -c 10 -f src/test/resources/groups.csv
    // | it means (-c) or count = 10; (-f) or file = "src/test/resources/groups.csv"
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ItemData> items = generateItems(count);
    if (format.equals("csv")) {
      saveAsCsv(items, new File (file));
    } else if (format.equals("xml")) {
      saveAsXml(items, new File (file));
    } else if (format.equals("json")) {
      saveAsJson(items, new File (file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private List<ItemData> generateItems(int count) {

    List<ItemData> items = new ArrayList<ItemData>();
    for (int i = 0; i < count; i++) {
      items.add(new ItemData().withFName(String.format("firstName %s", i))
              .withLName(String.format("lastName %s", i)).withMName(String.format("middleName %s", i)));
    }
    return items;
  }

  private void saveAsCsv(List<ItemData> items, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ItemData item : items) {
      writer.write(String.format("%s;%s;%s\n", item.getFirstname(), item.getMiddlename(),item.getLastname()));
    }
    writer.close();
  }

  private void saveAsXml(List<ItemData> items, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ItemData.class);
    String xml = xstream.toXML(items);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJson(List<ItemData> items, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(items);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }
}