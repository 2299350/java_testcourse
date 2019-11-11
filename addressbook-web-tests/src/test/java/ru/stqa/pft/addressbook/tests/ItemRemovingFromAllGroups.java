package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.ItemData;
import ru.stqa.pft.addressbook.model.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemRemovingFromAllGroups extends TestBase {

  private SessionFactory sessionFactory;

  @BeforeClass
  public void setUpConnection() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      MetadataSources a = new MetadataSources(registry);
      Metadata b = a.buildMetadata();
      sessionFactory = b.buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test
  public void testItemRemovingFromAllGroups() throws Exception {

    Items before = app.item().all();
    ItemData anyItem = before.iterator().next();

    //int itemId = anyItem.getId();
    int itemId = 161;

    //SQL to get the names of groups whose the user is added to by id
/*    SELECT group_list.group_name FROM group_list
    LEFT JOIN address_in_groups ON group_list.group_id = address_in_groups.group_id
    LEFT JOIN addressbook ON address_in_groups.id = addressbook.id
    WHERE addressbook.id = '161' AND group_list.deprecated = '0000-00-00';*/

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ItemData result = session.byId(ItemData.class).getReference(itemId);

    System.out.println(result.getGroups());

    session.getTransaction().commit();
    session.close();
  }

//    for (int i = 0; i < 2; i++) {
//      app.item().addItemToGroup(anyItem, groupNames.get(i));
//      System.out.println("User " + anyItem.getLastname() + " ,id = " + anyItem.getId() + " has been added to the " + groupNames.get(i) + ".");
//    }
//
//    Items after = app.db().items();
//    assertThat(after, equalTo(before));
}
