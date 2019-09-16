package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage("groups");
    initGroupCreation("new");
    fillGroupForm(new GroupData("Test1", "Test2", "Test3"));
    submitGroupCreation();
    returnToGroupPage();
  }
}