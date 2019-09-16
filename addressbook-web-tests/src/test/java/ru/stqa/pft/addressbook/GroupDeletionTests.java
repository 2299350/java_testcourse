package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testUntitledTestCase() throws Exception {

    gotoGroupPage("groups");
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }
}
