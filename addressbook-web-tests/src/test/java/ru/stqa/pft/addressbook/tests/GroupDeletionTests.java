package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testUntitledTestCase() throws Exception {

    app.gotoGroupPage("groups");
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
  }
}
